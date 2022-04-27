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
        // 分页查询商品
        List<CommodityOfListDTO> commodityOfList = tCommodityMapper.selectCommodityOfList(commoditySearchParam);
        // 根据每一个的id获取对应的映射
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
        // 根据id查询商品详情
        CommodityEntity commodity = tCommodityMapper.selectCommodityById(id);
        CommodityDetailVO commodityDetailVO = CommodityDetailVO.convertCommodityEntity(commodity);

        // 填充spu属性
        Long spuId = commodity.getSpuId();
        SpuAttributeEntity spuAttributeEntity = spuAttributeService.getSpuAttributesById(spuId);
        fillSpuAttribute(commodityDetailVO, spuAttributeEntity);

        // 填充其他属性，通过分类id以及商品的id查询这个分类下有什么属性
        Long categoryId = commodity.getCategoryId();
        List<AttributeNameDTO> attributeNameDTOS = categoryService.getAttributeNamesByCategoryId(categoryId);
        List<Long> attributeIds = attributeNameDTOS.stream().map(AttributeNameDTO::getId).toList();
        // 根据查询出来的属性找到对应的属性值
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

        //还有可能有其他单独的属性
        List<AttributeValueVO> otherAttributeValueVOS = otherAttributeService.getAttributeValuesByCommodityId(id);
        commodityDetailVO.setOtherAttributeDetailVOs(otherAttributeValueVOS);

        // 通过skuId找到对应的属性值
        List<SkuAttributeEntity> skuAttributes = skuAttributeService.getSkuAttributesOfCommodityId(id);
        List<Long> skuIds = skuAttributes.stream().map(SkuAttributeEntity::getId).toList();
        Map<Long, List<AttributeValueOfSkuVO>> skuIdToAttributeValueOfSkuVOMap = skuAttributeService.getSkuIdToAttributesMap(skuIds);

        // 组装skuVo
        List<SkuAttributeDetailVO> skuAttributeDetailVOs = SkuAttributeDetailVO.convertSkuAttributeEntities(skuAttributes, skuIdToAttributeValueOfSkuVOMap);
        commodityDetailVO.setSkuAttributeVOs(skuAttributeDetailVOs);

        // 商品的图片
        List<PictureVO> pictureVOs = pictureService.getPictureVOsByCommodityId(id);
        commodityDetailVO.setPictureVOs(pictureVOs);

        // 商品的详情

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

        // 将属性、商品、属性值关联起来
        List<AttributeValueSaveParam> attributeValueSaveParams = commoditySaveParam.getAttributeValueSaveParams();
        attributeValueService.saveAttributeValueByCommodityId(attributeValueSaveParams, id);

        // 保存商品的其他属性
        List<AttributeValueSaveParam> otherAttributeValueSaveParams = commoditySaveParam.getOtherAttributeValueSaveParams();
        otherAttributeService.saveAttributeValueByCommodityId(otherAttributeValueSaveParams, id);

        // 保存商品的sku属性
        List<SkuSaveParam> skuSaveParams = commoditySaveParam.getSkuSaveParams();
        skuAttributeService.saveSkuAttributeValue(skuSaveParams, id);

        // 保存商品的图片
        List<PictureSaveParam> albumPics = commoditySaveParam.getAlbumPics();
        pictureService.savePicturesByCommodityId(albumPics, id);

        // 保存商品的详情
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

        // 更新商品的基本详情
        CommoditySaveDTO commoditySaveDTO = CommoditySaveDTO.convertCommodityUpdateParam(commodityUpdateParam);
        tCommodityMapper.updateCommodity(commoditySaveDTO);

        // 更新商品的属性值
        List<AttributeValueSaveParam> attributeValueSaveParams = commodityUpdateParam.getAttributeValueSaveParams();
        attributeValueService.updateAttributeValues(attributeValueSaveParams);

        // 更新商品的其他属性
        List<AttributeValueSaveParam> otherAttributeValueSaveParams = commodityUpdateParam.getOtherAttributeValueSaveParams();
        otherAttributeService.updateAttributeValues(otherAttributeValueSaveParams);

        // 更新商品的sku属性值
        List<SkuSaveParam> skuSaveParams = commodityUpdateParam.getSkuSaveParams();
        skuAttributeService.updateSkuAttributeValue(skuSaveParams, commodityId);

        // 更新商品的照片
        List<PictureSaveParam> pictureSaveParams = commodityUpdateParam.getAlbumPics();
        pictureService.updatePictureService(pictureSaveParams, commodityId);

        // 更新商品的详情

    }

    @Transactional
    void deleteCommodity(Long commodityId) {

        // 删除商品的基本信息
        tCommodityMapper.deleteCommodity(commodityId);
        // 删除商品关联的属性值
        attributeValueService.deleteByCommodityId(commodityId);
        // 删除商品附加属性
        otherAttributeService.deleteByCommodityId(commodityId);
        // 商品的图片
        pictureService.deleteByCommodityId(commodityId);
        // 删除商品的详情
    }


    @Override
    public void updateCommodityStatus(CommodityStatusParam commodityStatusParam) {
        if (commodityStatusParam == null) {
            return;
        }
        if (commodityStatusParam.getId() == null) {
            throw new BusinessException("id不能为null");
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
