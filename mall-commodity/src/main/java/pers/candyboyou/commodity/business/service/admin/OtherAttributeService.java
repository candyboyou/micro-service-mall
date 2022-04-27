package pers.candyboyou.commodity.business.service.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.candyboyou.common.framework.model.vo.PageResult;
import org.apache.ibatis.annotations.Param;
import pers.candyboyou.commodity.business.model.param.admin.AttributeValueSaveParam;
import pers.candyboyou.commodity.business.model.vo.admin.AttributeValueVO;
import pers.candyboyou.commodity.business.model.vo.admin.SkuAttributeVO;

import java.util.List;

public interface OtherAttributeService {

    /**
     * 根据商品id获取对应的属性值list
     */
    List<AttributeValueVO> getAttributeValuesByCommodityId(Long commodityId);

    /**
     * 保存商品附加的属性值
     */
    void saveAttributeValueByCommodityId(List<AttributeValueSaveParam> otherAttributeValueSaveParams, Long commodityId);

    /**
     * 更新商品的属性值
     */
    void updateAttributeValues(List<AttributeValueSaveParam> otherAttributeValueSaveParams);

    /**
     * 删除商品关联的属性
     */
    void deleteByCommodityId(Long commodityId);

}
