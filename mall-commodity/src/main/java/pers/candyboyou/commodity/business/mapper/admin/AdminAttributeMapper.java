package pers.candyboyou.commodity.business.mapper.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import org.apache.ibatis.annotations.Param;
import pers.candyboyou.commodity.business.model.dto.AttributeDTO;
import pers.candyboyou.commodity.business.model.param.admin.AttrParam;

import java.util.List;

public interface AdminAttributeMapper {

    void insertAttribute(@Param("param") AttrParam attrParam);

    void deleteAttrById(Long id);

    void updateAttr(@Param("param") AttrParam AttrParam);

    List<AttributeDTO> selectAttributeDTOS(Long attributeId, @Param("param") QueryParam queryParam);

    int selectAttributeDTOSCount(Long attributeId);
}
