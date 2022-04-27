package pers.candyboyou.commodity.business.mapper.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.candyboyou.commodity.business.model.dto.AttributeIdWithIsSaleDTO;
import pers.candyboyou.commodity.business.model.dto.AttributeNameDTO;
import pers.candyboyou.commodity.business.model.dto.AttributeOfListDTO;
import pers.candyboyou.commodity.business.model.entity.AttributeEntity;
import pers.candyboyou.commodity.business.model.param.admin.AttrParam;
import pers.candyboyou.commodity.business.model.param.admin.AttrSearchParam;

import java.util.List;

@Mapper
@Repository
public interface TCommodityAttributeMapper {

    void insertAttribute(@Param("param") AttributeEntity attributeEntity);

    void deleteAttrById(Long id);

    void updateAttr(@Param("param") AttributeEntity attributeEntity);

    List<AttributeEntity> selectAttributeDTOs(Long attributeId, @Param("param") QueryParam queryParam);

    int selectAttributeDTOsCount(Long attributeId);

    List<AttributeIdWithIsSaleDTO> selectAttributeIdWithIsSale(@Param("attributeIds") List<Long> attributeIds);

    /**
     * 根据属性Id批量获取对应的名称
     */
    List<AttributeNameDTO> batchSelectAttributeNameByAttributeIds(@Param("attributeIds") List<Long> attributeIds);

    /**
     * 根据商品id删除属性
     */
    void deleteByCommodityId(Long commodityId);

    List<AttributeOfListDTO> selectAttributeDTOsByParam(@Param("param") AttrSearchParam attrSearchParam);

    int selectAttributeDTOsCountByParam(@Param("param") AttrSearchParam attrSearchParam);

    AttributeEntity selectAttributeById(@Param("id")Long attributeId);

    List<AttributeNameDTO> batchSelectAllAttributeName();

}
