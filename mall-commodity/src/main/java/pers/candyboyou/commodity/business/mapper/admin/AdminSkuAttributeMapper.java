package pers.candyboyou.commodity.business.mapper.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import org.apache.ibatis.annotations.Param;
import pers.candyboyou.commodity.business.model.dto.SkuAttributeDTO;
import pers.candyboyou.commodity.business.model.entity.SkuAttributeEntity;
import pers.candyboyou.commodity.business.model.param.admin.SkuAttrParam;
import pers.candyboyou.commodity.business.model.param.admin.SkuAttributeOfCommoditySaveParam;

import java.util.List;

public interface AdminSkuAttributeMapper {

    void insertSkuAttr(@Param("param") SkuAttrParam skuAttrParam);

    void deleteSupAttrById(Long id);

    void updateSupAttr(@Param("param") SkuAttrParam skuAttrParam);

    List<SkuAttributeDTO> selectSkuAttributeDTOS(Long attributeId, @Param("queryParam") QueryParam queryParam);

    int selectSkuAttributeDTOSCount(Long attributeId);

    List<SkuAttributeEntity> selectSkuAttributeByCommodityId(Long id);

    List<Long> saveSkuAttributeValue(@Param("params") List<SkuAttributeOfCommoditySaveParam> skuAttributeOfCommoditySaveParams, Long id);
}
