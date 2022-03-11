package pers.candyboyou.commodity.business.mapper.admin;

import org.apache.ibatis.annotations.Param;
import pers.candyboyou.commodity.business.model.param.admin.SpuAttributeValueSaveParam;

import java.util.List;

public interface TCommodityAttributeValueMapper {

    void saveAttributeValueOfCommodity(@Param("saveParam") List<SpuAttributeValueSaveParam> attributeOfCommoditySaveParams, Long commodityId);

}
