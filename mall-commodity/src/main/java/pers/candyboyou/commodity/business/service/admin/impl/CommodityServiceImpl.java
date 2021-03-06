package pers.candyboyou.commodity.business.service.admin.impl;

import io.candyboyou.common.expection.BusinessException;
import io.candyboyou.common.framework.model.vo.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.candyboyou.commodity.business.enums.NewStatusEnum;
import pers.candyboyou.commodity.business.enums.PublishStatusEnum;
import pers.candyboyou.commodity.business.enums.RecommendStatusEnum;
import pers.candyboyou.commodity.business.enums.VerifyStatusEnum;
import pers.candyboyou.commodity.business.mapper.admin.TCommodityMapper;
import pers.candyboyou.commodity.business.model.dto.AttributeNameDTO;
import pers.candyboyou.commodity.business.model.dto.AttributeValueDTO;
import pers.candyboyou.commodity.business.model.dto.CommodityOfListDTO;
import pers.candyboyou.commodity.business.model.dto.CommoditySaveDTO;
import pers.candyboyou.commodity.business.model.entity.CommodityEntity;
import pers.candyboyou.commodity.business.model.entity.SkuAttributeEntity;
import pers.candyboyou.commodity.business.model.entity.SpuAttributeEntity;
import pers.candyboyou.commodity.business.model.param.admin.*;
import pers.candyboyou.commodity.business.model.vo.admin.*;
import pers.candyboyou.commodity.business.service.admin.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private AttributeValueService attributeValueService;

    @Autowired
    private OtherAttributeService otherAttributeService;

    @Autowired
    private SpuAttributeService spuAttributeService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private SkuAttributeService skuAttributeService;

    @Autowired
    private TCommodityMapper tCommodityMapper;

    @Override
    public PageResult<CommodityOfListVO> paginateGetCommodities(CommoditySearchParam commoditySearchParam) {
        List<CommodityOfListVO> commodityOfListVOs = new ArrayList<>();
        // ??????????????????
        List<CommodityOfListDTO> commodityOfList = tCommodityMapper.selectCommodityOfList(commoditySearchParam);
        // ??????????????????id?????????????????????
        List<Long> spuIds = new ArrayList<>();
        List<Long> categoryIds = new ArrayList<>();
        List<Long> commodityIds = new ArrayList<>();
        for (CommodityOfListDTO commodityOfListDTO : commodityOfList) {
            if (commodityOfListDTO == null) {
                continue;
            }
            Long spuId = commodityOfListDTO.getSpuId();
            if (spuId != null) {
                spuIds.add(spuId);
            }
            Long categoryId = commodityOfListDTO.getCategoryID();
            if (categoryId != null) {
                categoryIds.add(categoryId);
            }
            commodityIds.add(commodityOfListDTO.getId());
        }
        Map<Long, SpuAttributeEntity> spuAttributeEntityMap = spuAttributeService.getSpuIdWithSpuByIds(spuIds);
        Map<Long, String> categoryIdToNameMap = categoryService.getCategoryNamesByIds(categoryIds);
//        Map<Long, String> descIdWithPictureMap = pictureService.getMainPictureUrlByCommodityIds(commodityIds);
        for (CommodityOfListDTO commodityOfListDTO : commodityOfList) {
            CommodityOfListVO commodityOfListVO =  CommodityOfListVO.convertCommodityOfListDTO(commodityOfListDTO);
            SpuAttributeEntity spuAttributeEntity = spuAttributeEntityMap.get(commodityOfListDTO.getSpuId());
            if (spuAttributeEntity != null) {
                commodityOfListVO.setSpuSale(spuAttributeEntity.getSale());
            }
            String categoryName = categoryIdToNameMap.get(commodityOfListDTO.getCategoryID());
            commodityOfListVO.setCategory(categoryName);
//            String pictureUrl = descIdWithPictureMap.get(commodityOfListDTO.getId());
//            commodityOfListVO.setPictureUrl(pictureUrl);
            commodityOfListVOs.add(commodityOfListVO);
        }
        int total = tCommodityMapper.selectCommodityCounts(commoditySearchParam);

        PageResult<CommodityOfListVO> commodityOfListVOPageResult = new PageResult<>();
        commodityOfListVOPageResult.setList(commodityOfListVOs);
        commodityOfListVOPageResult.setPageNum(commoditySearchParam.getPageNum());
        commodityOfListVOPageResult.setPageSize(commodityOfListVOs.size());
        commodityOfListVOPageResult.setTotal(total);
        return commodityOfListVOPageResult;
    }

    @Override
    public CommodityDetailVO getCommodityDetailVO(Long id) {
        // ??????id??????????????????
        CommodityEntity commodity = tCommodityMapper.selectCommodityById(id);
        CommodityDetailVO commodityDetailVO = CommodityDetailVO.convertCommodityEntity(commodity);

        // ??????spu??????
        Long spuId = commodity.getSpuId();
        SpuAttributeEntity spuAttributeEntity = spuAttributeService.getSpuAttributesById(spuId);
        fillSpuAttribute(commodityDetailVO, spuAttributeEntity);

        // ?????????????????????????????????id???????????????id????????????????????????????????????
        Long categoryId = commodity.getCategoryId();
        List<AttributeNameDTO> attributeNameDTOS = categoryService.getAttributeNamesByCategoryId(categoryId);
        List<Long> attributeIds = attributeNameDTOS.stream().map(AttributeNameDTO::getId).toList();
        // ???????????????????????????????????????????????????
        Map<Long, AttributeValueDTO> attributeIdToValueMap = attributeValueService.getAttributeValuesByAttributeIdsAndCommodityId(attributeIds, id);
        List<AttributeValueVO> attributeValueVOs = new ArrayList<>();
        for (AttributeNameDTO attributeNameDTO : attributeNameDTOS) {
            AttributeValueVO attributeValueVO = new AttributeValueVO();

            Long attributeId = attributeNameDTO.getId();
            attributeValueVO.setAttributeId(attributeId);
            attributeValueVO.setAttributeName(attributeNameDTO.getName());

            AttributeValueDTO attributeValueDTO = attributeIdToValueMap.get(attributeId);
            attributeValueVO.setAttributeValueId(attributeValueDTO.getId());
            attributeValueVO.setAttributeValue(attributeValueDTO.getValue());

            attributeValueVOs.add(attributeValueVO);
        }
        commodityDetailVO.setAttributeDetailVOs(attributeValueVOs);

        //????????????????????????????????????
        List<AttributeValueVO> otherAttributeValueVOS = otherAttributeService.getAttributeValuesByCommodityId(id);
        commodityDetailVO.setOtherAttributeDetailVOs(otherAttributeValueVOS);

        // ??????skuId????????????????????????
        List<SkuAttributeEntity> skuAttributes = skuAttributeService.getSkuAttributesOfCommodityId(id);
        List<Long> skuIds = skuAttributes.stream().map(SkuAttributeEntity::getId).toList();
        Map<Long, List<AttributeValueOfSkuVO>> skuIdToAttributeValueOfSkuVOMap = skuAttributeService.getSkuIdToAttributesMap(skuIds);

        // ??????skuVo
        List<SkuAttributeDetailVO> skuAttributeDetailVOs = SkuAttributeDetailVO.convertSkuAttributeEntities(skuAttributes, skuIdToAttributeValueOfSkuVOMap);
        commodityDetailVO.setSkuAttributeVOs(skuAttributeDetailVOs);

        // ???????????????
        List<PictureVO> pictureVOs = pictureService.getPictureVOsByCommodityId(id);
        commodityDetailVO.setPictureVOs(pictureVOs);

        // ???????????????

        return commodityDetailVO;
    }

    private void fillSpuAttribute(CommodityDetailVO commodityDetailVO, SpuAttributeEntity spuAttributeEntity) {
        if (spuAttributeEntity == null) {
            return;
        }
        commodityDetailVO.setSpuPrice(spuAttributeEntity.getPrice());
        commodityDetailVO.setSpuStock(spuAttributeEntity.getStock());
        commodityDetailVO.setSpuLowStock(spuAttributeEntity.getLowStock());
    }

    @Override
    @Transactional
    public Long newCommodity(CommoditySaveParam commoditySaveParam) {
        if (commoditySaveParam == null) {
            return null;
        }

        CommoditySaveDTO commoditySaveDTO = CommoditySaveDTO.convertCommoditySaveParam(commoditySaveParam);
        Long id = tCommodityMapper.saveCommodity(commoditySaveDTO);

        // ??????????????????????????????????????????
        List<AttributeValueSaveParam> attributeValueSaveParams = commoditySaveParam.getAttributeValueSaveParams();
        attributeValueService.saveAttributeValueByCommodityId(attributeValueSaveParams, id);

        // ???????????????????????????
        List<AttributeValueSaveParam> otherAttributeValueSaveParams = commoditySaveParam.getOtherAttributeValueSaveParams();
        otherAttributeService.saveAttributeValueByCommodityId(otherAttributeValueSaveParams, id);

        // ???????????????sku??????
        List<SkuSaveParam> skuSaveParams = commoditySaveParam.getSkuSaveParams();
        skuAttributeService.saveSkuAttributeValue(skuSaveParams, id);

        // ?????????????????????
        List<PictureSaveParam> albumPics = commoditySaveParam.getAlbumPics();
        pictureService.savePicturesByCommodityId(albumPics, id);

        // ?????????????????????
        return commoditySaveDTO.getId();
    }

    @Override
    public void updateCommodity(CommodityUpdateParam commodityUpdateParam) {
        if (commodityUpdateParam == null) {
            return;
        }

        Long commodityId = commodityUpdateParam.getId();
        if (commodityUpdateParam.getIsDelete() == 1) {
            deleteCommodity(commodityId);
        }

        // ???????????????????????????
        CommoditySaveDTO commoditySaveDTO = CommoditySaveDTO.convertCommodityUpdateParam(commodityUpdateParam);
        tCommodityMapper.updateCommodity(commoditySaveDTO);

        // ????????????????????????
        List<AttributeValueSaveParam> attributeValueSaveParams = commodityUpdateParam.getAttributeValueSaveParams();
        attributeValueService.updateAttributeValues(attributeValueSaveParams);

        // ???????????????????????????
        List<AttributeValueSaveParam> otherAttributeValueSaveParams = commodityUpdateParam.getOtherAttributeValueSaveParams();
        otherAttributeService.updateAttributeValues(otherAttributeValueSaveParams);

        // ???????????????sku?????????
        List<SkuSaveParam> skuSaveParams = commodityUpdateParam.getSkuSaveParams();
        skuAttributeService.updateSkuAttributeValue(skuSaveParams, commodityId);

        // ?????????????????????
        List<PictureSaveParam> pictureSaveParams = commodityUpdateParam.getAlbumPics();
        pictureService.updatePictureService(pictureSaveParams, commodityId);

        // ?????????????????????

    }

    @Transactional
    void deleteCommodity(Long commodityId) {

        // ???????????????????????????
        tCommodityMapper.deleteCommodity(commodityId);
        // ??????????????????????????????
        attributeValueService.deleteByCommodityId(commodityId);
        // ????????????????????????
        otherAttributeService.deleteByCommodityId(commodityId);
        // ???????????????
        pictureService.deleteByCommodityId(commodityId);
        // ?????????????????????
    }


    @Override
    public void updateCommodityStatus(CommodityStatusParam commodityStatusParam) {
        if (commodityStatusParam == null) {
            return;
        }
        if (commodityStatusParam.getId() == null) {
            throw new BusinessException("id?????????null");
        }
        tCommodityMapper.updateStatusOfCommodity(commodityStatusParam);
    }

    @Override
    public AllListParamOfCommodityVO getAllParamList() {
        AllListParamOfCommodityVO allListParamOfCommodityVO = new AllListParamOfCommodityVO();
        allListParamOfCommodityVO.setCategoryList(categoryService.getAllCategoryIdAndName());
        allListParamOfCommodityVO.setNewStatusList(NewStatusEnum.getAllList());
        allListParamOfCommodityVO.setPublishStatusList(PublishStatusEnum.getAllList());
        allListParamOfCommodityVO.setRecommendStatusList(RecommendStatusEnum.getAllList());
        allListParamOfCommodityVO.setVerifyStatusList(VerifyStatusEnum.getAllList());
        return allListParamOfCommodityVO;
    }
}
