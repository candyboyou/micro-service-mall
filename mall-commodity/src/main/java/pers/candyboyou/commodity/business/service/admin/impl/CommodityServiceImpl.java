package pers.candyboyou.commodity.business.service.admin.impl;

import io.candyboyou.common.expection.BusinessException;
import io.candyboyou.common.framework.model.vo.ListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.candyboyou.commodity.business.mapper.admin.TCommodityMapper;
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
    private SpuAttributeService spuAttributeService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DescriptionService descriptionService;

    @Autowired
    private SkuAttributeService skuAttributeService;

    @Autowired
    private TCommodityMapper tCommodityMapper;

    @Override
    public ListVO<CommodityOfListVO> getCommodityVOS(CommoditySearchParam commoditySearchParam) {
        List<CommodityOfListVO> commodityOfListVOS = new ArrayList<>();
        // 分页查询商品
        List<CommodityOfListDTO> commodityOfList = tCommodityMapper.selectCommodityOfList(commoditySearchParam);
        // 根据每一个的id获取对应的映射
        List<Long> spuIds = new ArrayList<>();
        List<Long> categoryIds = new ArrayList<>();
        List<Long> descriptionIds = new ArrayList<>();
        for (CommodityOfListDTO commodityOfListDTO : commodityOfList) {
            if (commodityOfListDTO == null) {
                continue;
            }
            Long spuId = commodityOfListDTO.getSpuId();
            if (spuId != null) {
                spuIds.add(spuId);
            }
            Long categoryId = commodityOfListDTO.getCategoryId();
            if (categoryId != null) {
                categoryIds.add(categoryId);
            }
            Long descriptionId = commodityOfListDTO.getDescriptionId();
            if (descriptionId != null) {
                descriptionIds.add(descriptionId);
            }
        }
        Map<Long, SpuAttributeEntity> spuAttributeEntityMap = spuAttributeService.getSpuIdWithSpuByIds(spuIds);
        Map<Long, String> categoryIdToNameMap = categoryService.getCategoryNamesByIds(categoryIds);
        Map<Long, String> descIdWithImgMap = descriptionService.getDescIdWithImgByIds(descriptionIds);
        for (CommodityOfListDTO commodityOfListDTO : commodityOfList) {
            CommodityOfListVO commodityOfListVO =  CommodityOfListVO.convertCommodityOfListDTO(commodityOfListDTO);
            SpuAttributeEntity spuAttributeEntity = spuAttributeEntityMap.get(commodityOfListDTO.getSpuId());
            commodityOfListVO.setSpuPrice(spuAttributeEntity.getPrice());
            commodityOfListVO.setSpuSale(spuAttributeEntity.getSale());
            String categoryName = categoryIdToNameMap.get(commodityOfListDTO.getCategoryId());
            commodityOfListVO.setCategoryName(categoryName);
            String imgUrl = descIdWithImgMap.get(commodityOfListDTO.getDescriptionId());
            commodityOfListVO.setImgUrl(imgUrl);
            commodityOfListVOS.add(commodityOfListVO);
        }
        int total = tCommodityMapper.selectCommodityCounts(commoditySearchParam);

        ListVO<CommodityOfListVO> commodityOfListVOListVO = new ListVO<>();
        commodityOfListVOListVO.setList(commodityOfListVOS);
        commodityOfListVOListVO.setPageNum(commoditySearchParam.getPageNum());
        commodityOfListVOListVO.setPageSize(commodityOfListVOS.size());
        commodityOfListVOListVO.setTotal(total);
        return commodityOfListVOListVO;
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

        // 填充其他属性，通过分类id查询普通属性list
        Long categoryId = commodity.getCategoryId();
        List<AttributeValueVO> attributeValueVOS = categoryService.getAttributeValuesById(categoryId);
        commodityDetailVO.setAttributeDetailVOS(attributeValueVOS);

        //还有可能有其他单独的属性

        // 通过skuId找到对应的属性值
        List<SkuAttributeEntity> skuAttributes = skuAttributeService.getSkuAttributesOfCommodityId(id);
        List<Long> skuIds = skuAttributes.stream().map(SkuAttributeEntity::getId).toList();
        Map<Long, List<AttributeValueOfSkuVO>> skuIdToAttributeValueOfSkuVOMap = skuAttributeService.getSkuIdToAttributesMap(skuIds);
        // 组装skuVo
        List<SkuAttributeDetailVO> skuAttributeDetailVOS = SkuAttributeDetailVO.convertSkuAttributeEntities(skuAttributes, skuIdToAttributeValueOfSkuVOMap);
        commodityDetailVO.setSkuAttributeVOS(skuAttributeDetailVOS);

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
    public void saveOrUpdateCommodity(CommoditySaveParam commoditySaveParam) {
        // 如果id为null，那么说明是新增的
        if (commoditySaveParam == null) {
            return;
        }

        CommoditySaveDTO commoditySaveDTO = CommoditySaveDTO.convertCommoditySaveParam(commoditySaveParam);
        Long id = commoditySaveParam.getId();
        if (id == null) {
            id = tCommodityMapper.saveCommodity(commoditySaveDTO);
        } else {
            tCommodityMapper.updateCommodity(commoditySaveDTO);
        }

        // 将属性、商品、属性值关联起来
        List<SpuAttributeValueSaveParam> spuAttributeValueSaveParams = commoditySaveParam.getSpuAttributeValueSaveParams();
        attributeService.saveAttributeValue(spuAttributeValueSaveParams, id);

        // 保存商品的sku属性
        List<SkuSaveParam> skuSaveParams = commoditySaveParam.getSkuSaveParams();
        skuAttributeService.saveSkuAttributeValue(skuSaveParams, id);
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
}
