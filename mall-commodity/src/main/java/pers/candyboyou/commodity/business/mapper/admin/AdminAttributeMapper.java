package pers.candyboyou.commodity.business.mapper.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import org.apache.ibatis.annotations.Param;
import pers.candyboyou.commodity.business.model.dto.AttributeIdWithIsSaleDTO;
import pers.candyboyou.commodity.business.model.entity.AttributeEntity;
import pers.candyboyou.commodity.business.model.param.admin.AttrParam;

import java.util.List;

public interface AdminAttributeMapper {

    void insertAttribute(@Param("param") AttrParam attrParam);

    void deleteAttrById(Long id);

    void updateAttr(@Param("param") AttrParam AttrParam);

    List<AttributeEntity> selectAttributeDTOS(Long attributeId, @Param("param") QueryParam queryParam);

    int selectAttributeDTOSCount(Long attributeId);

    List<AttributeIdWithIsSaleDTO> selectAttributeIdWithIsSale(@Param("attributeIds") List<Long> attributeIds);
}
