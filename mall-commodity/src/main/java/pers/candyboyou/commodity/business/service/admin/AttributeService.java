package pers.candyboyou.commodity.business.service.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.candyboyou.common.framework.model.vo.ListVO;
import pers.candyboyou.commodity.business.model.param.admin.AttrParam;
import pers.candyboyou.commodity.business.model.param.admin.SkuAttrParam;
import pers.candyboyou.commodity.business.model.vo.admin.AttributeVO;
import pers.candyboyou.commodity.business.model.vo.admin.SkuAttributeVO;

import java.util.List;
import java.util.Map;

public interface AttributeService {

    void saveOrUpdateSkuAttr(SkuAttrParam skuAttrParam);

    ListVO<SkuAttributeVO> getSkuAttributesById(Long attributeId, QueryParam queryParam);

    void saveOrUpdateAttr(AttrParam AttrParam);

    ListVO<AttributeVO> getAttributeVOsById(Long attributeId, QueryParam queryParam);

    Map<Long, Integer> getAttributeIdToIsSaleMap(List<Long> attributeIds);

    /**
     * 根据attributeId批量获取属性的名称
     * @return
     */
    Map<Long, String> getAttributeNamesByAttributeIds(List<Long> attributeIds);

    /**
     * 删除商品对应的属性
     */
    void deleteByCommodityId(Long commodityId);
}
