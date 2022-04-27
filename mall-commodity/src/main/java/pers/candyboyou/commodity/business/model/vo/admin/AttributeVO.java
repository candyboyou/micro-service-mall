package pers.candyboyou.commodity.business.model.vo.admin;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import io.candyboyou.common.utils.CollectionUtils;
import io.candyboyou.common.utils.StringUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.model.dto.AttributeOfListDTO;
import pers.candyboyou.commodity.business.model.entity.AttributeEntity;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Data
public class AttributeVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 2146651231533306770L;

    @ApiModelProperty("类型id")
    private Long id;

    @ApiModelProperty("属性名称")
    private String name;

    @ApiModelProperty("是否是销售属性")
    private Integer isSale;

    @ApiModelProperty("属性选择类型")
    private Integer selectType;

    @ApiModelProperty("属性录入方式")
    private Integer inputType;

    @ApiModelProperty("是否支持检索")
    private Integer searchType;

    @ApiModelProperty("是否启用")
    private Integer isValid;

    private List<String> attributeValues;

    public static List<AttributeVO> convertAttributeEntities(List<AttributeEntity> attributeEntities) {
        if (CollectionUtils.isEmpty(attributeEntities)) {
            return new ArrayList<>();
        }
        List<AttributeVO> attributeVOs = new ArrayList<>();
        for (AttributeEntity attributeEntity : attributeEntities) {
            AttributeVO attributeVO = convertAttributeEntity(attributeEntity);
            attributeVOs.add(attributeVO);
        }
        return attributeVOs;
    }

    public static AttributeVO convertAttributeEntity(AttributeEntity attributeEntity) {
        AttributeVO attributeVO = new AttributeVO();
        attributeVO.setId(attributeEntity.getId());
        attributeVO.setName(attributeEntity.getName());
        attributeVO.setSearchType(attributeEntity.getSearchType());
        attributeVO.setInputType(attributeEntity.getInputType());
        attributeVO.setSearchType(attributeEntity.getSearchType());
        attributeVO.setIsValid(attributeEntity.getIsValid());
        attributeVO.setIsSale(attributeEntity.getIsSale());
        String attributeValues = attributeEntity.getAttributeValues();
        if (StringUtils.isNotBlank(attributeValues)) {
            Type type = new TypeToken<List<String>>() {}.getType();
            List<String> attributeValuesStr = new Gson().fromJson(attributeValues, type);
            attributeVO.setAttributeValues(attributeValuesStr);
        }
        return attributeVO;
    }
}
