package pers.candyboyou.commodity.business.mapper.admin;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.candyboyou.commodity.business.model.entity.OtherAttributeRelationEntity;
import pers.candyboyou.commodity.business.model.param.admin.AttributeValueSaveParam;

import java.util.List;

@Repository
public interface TCommodityOtherAttributeRelationMapper {

    List<OtherAttributeRelationEntity> selectEntityByCommodityId(Long commodityId);

    void insertAttributeValueOfCommodity(@Param("saveParams") List<AttributeValueSaveParam> otherAttributeValueSaveParams, Long commodityId);

    /**
     * 批量更新商品的附加属性值
     */
    void updateAttributeValues(@Param("updateParams") List<AttributeValueSaveParam> otherAttributeValueSaveParams);

    /**
     * 根据商品id删除对应属性
     */
    void deleteByCommodityId(Long commodityId);
}
