package pers.candyboyou.commodity.business.model.entity;

import com.google.gson.Gson;
import io.candyboyou.common.framework.model.base.Entity;
import io.candyboyou.common.utils.CollectionUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.candyboyou.commodity.business.model.param.admin.AttrParam;

import java.io.Serial;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AttributeEntity extends Entity {

    @Serial
    private static final long serialVersionUID = 8821212791054366048L;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 是否是销售属性
     */
    private Integer isSale;

    /**
     * 属性选择类型
     */
    private Integer selectType;

    /**
     * 属性值输入类型
     */
    private Integer inputType;

    /**
     * 检索类型
     */
    private Integer searchType;

    /**
     * 预置属性值
     */
    private String attributeValues;

    public static AttributeEntity convertAttrParam(AttrParam attrParam) {
        AttributeEntity attributeEntity = new AttributeEntity();
        attributeEntity.setName(attrParam.getName());
        attributeEntity.setInputType(attrParam.getInputType());
        attributeEntity.setIsSale(attrParam.getIsSale());
        attributeEntity.setSelectType(attrParam.getSelectType());
        attributeEntity.setSearchType(attrParam.getSearchType());
        List<String> attributeValues = attrParam.getAttributeValues();
        if (CollectionUtils.isNotEmpty(attributeValues)) {
            String attributeValuesStr = new Gson().toJson(attributeValues);
            attributeEntity.setAttributeValues(attributeValuesStr);
        }
        attributeEntity.setId(attrParam.getId());
        attributeEntity.setIsValid(attrParam.getIsValid());

        return attributeEntity;
    }
}
