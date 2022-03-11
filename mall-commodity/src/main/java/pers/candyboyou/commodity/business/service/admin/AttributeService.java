package pers.candyboyou.commodity.business.service.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.candyboyou.common.framework.model.vo.ListVO;
import pers.candyboyou.commodity.business.model.param.admin.AttrParam;
import pers.candyboyou.commodity.business.model.param.admin.SkuAttrParam;
import pers.candyboyou.commodity.business.model.param.admin.SpuAttributeValueSaveParam;
import pers.candyboyou.commodity.business.model.vo.admin.AttributeVO;
import pers.candyboyou.commodity.business.model.vo.admin.SkuAttributeVO;

import java.util.List;
import java.util.Map;

public interface AttributeService {

    void saveOrUpdateSkuAttr(SkuAttrParam skuAttrParam);

    ListVO<SkuAttributeVO> getSkuAttributesById(Long attributeId, QueryParam queryParam);

    void saveOrUpdateAttr(AttrParam AttrParam);

    ListVO<AttributeVO> getAttributeVOSById(Long attributeId, QueryParam queryParam);

    Map<Long, Integer> getAttributeIdToIsSaleMap(List<Long> attributeIds);

    void saveAttributeValue(List<SpuAttributeValueSaveParam> spuAttributeValueSaveParams, Long id);
}
