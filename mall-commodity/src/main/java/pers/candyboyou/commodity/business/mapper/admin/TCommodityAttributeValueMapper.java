package pers.candyboyou.commodity.business.mapper.admin;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.candyboyou.commodity.business.model.dto.AttributeValueDTO;
import pers.candyboyou.commodity.business.model.dto.AttributeWithValueDTO;
import pers.candyboyou.commodity.business.model.param.admin.AttributeValueSaveParam;

import java.util.List;

@Repository
public interface TCommodityAttributeValueMapper {

    /**
     * 保存商品的属性值
     */
    void insertAttributeValueOfCommodity(@Param("saveParams") List<AttributeValueSaveParam> attributeOfCommoditySaveParams, Long commodityId);

    /**
     * 更新商品的属性值
     */
    void updateAttributeValues(@Param("updateParams") List<AttributeValueSaveParam> attributeValueSaveParams);

    /**
     * 根据属性值ID批量查询对应的属性值名
     */
    List<AttributeValueDTO> batchSelectAttributeValueDTOByValueIds(@Param("valueIds") List<Long> attributeValueIds);

    /**
     * 根据商品id以及属性id批量获取属性值对象
     */
    List<AttributeWithValueDTO> batchSelectAttributeWithValueDTOByAttributeIdAndCommodity(@Param("attributeIds") List<Long> attributeIds, Long commodityId);

    /**
     * 根据商品的id删除对应的商品
     */
    void deleteByCommodityId(Long commodityId);

}
