package pers.candyboyou.commodity.business.mapper.admin;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.candyboyou.commodity.business.model.dto.SkuAttributeRelationDTO;
import pers.candyboyou.commodity.business.model.dto.SkuAttributeValueDTO;

import java.util.List;

@Repository
public interface TCommoditySkuAttributeRelationMapper {

    /**
     * 根据skuId批量查询sku对应的属性以及属性值
     */
    List<SkuAttributeRelationDTO> selectAttributesOfSkuBySkuIds(List<Long> skuIds);

    /**
     * 批量插入sku对应的属性以及属性值
     */
    void batchInsertSkuAttribute(@Param("params") List<SkuAttributeValueDTO> skuAttributeValueDTOs);

    /**
     * 更新sku对应属性的属性值
     */
    void batchUpdateAttributeValueOfSku(@Param("updateParams") List<SkuAttributeValueDTO> skuAttributeValueDTOs);
}
