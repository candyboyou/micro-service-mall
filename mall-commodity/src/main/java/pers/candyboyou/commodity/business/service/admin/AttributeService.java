package pers.candyboyou.commodity.business.service.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.candyboyou.common.framework.model.vo.ListVO;
import pers.candyboyou.commodity.business.model.param.admin.AttrParam;
import pers.candyboyou.commodity.business.model.param.admin.SkuAttrParam;
import pers.candyboyou.commodity.business.model.vo.admin.AttributeVO;
import pers.candyboyou.commodity.business.model.vo.admin.SkuAttributeVO;

public interface AttributeService {

    void saveOrUpdateSkuAttr(SkuAttrParam skuAttrParam);

    ListVO<SkuAttributeVO> getSkuAttributesById(Long attributeId, QueryParam queryParam);

    void saveOrUpdateAttr(AttrParam AttrParam);

    ListVO<AttributeVO> getAttributeVOSById(Long attributeId, QueryParam queryParam);
}
