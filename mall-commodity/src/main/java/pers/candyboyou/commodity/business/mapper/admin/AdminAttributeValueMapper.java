package pers.candyboyou.commodity.business.mapper.admin;

import org.apache.ibatis.annotations.Param;
import pers.candyboyou.commodity.business.model.param.admin.AttributeOfCommoditySaveParam;

import java.util.List;

public interface AdminAttributeValueMapper {

    void saveAttributeValueOfCommodity(@Param("saveParam") List<AttributeOfCommoditySaveParam> attributeOfCommoditySaveParams, Long commodityId);

}
