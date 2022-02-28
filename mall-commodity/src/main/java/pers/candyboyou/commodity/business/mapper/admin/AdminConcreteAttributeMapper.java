package pers.candyboyou.commodity.business.mapper.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import pers.candyboyou.commodity.business.model.param.admin.ConcreteAttrParam;
import pers.candyboyou.commodity.business.model.vo.ConcreteAttributeVO;

import java.util.List;

public interface AdminConcreteAttributeMapper {

    void insertConcreteAttr(ConcreteAttrParam concreteAttrParam);

    void deleteConcreteAttrById(Long id);

    void updateConcreteAttr(ConcreteAttrParam concreteAttrParam);

    List<ConcreteAttributeVO> selectConcreteAttributeDTOS(Long attributeId, QueryParam queryParam);

    int selectConcreteAttributeDTOSCount(Long attributeId);
}
