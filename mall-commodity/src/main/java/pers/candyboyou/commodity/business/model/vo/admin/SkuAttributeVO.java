package pers.candyboyou.commodity.business.model.vo.admin;

import io.candyboyou.common.utils.CollectionUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.model.dto.SkuAttributeDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class SkuAttributeVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1345521839145028938L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("sku属性名称")
    private String name;

    @ApiModelProperty("商品属性id")
    private Long attrId;

    @ApiModelProperty("属性值id list")
    private List<Long> valueIds;

    public static List<SkuAttributeVO> convertSkuAttributeDTOs(List<SkuAttributeDTO> skuAttributeDTOList) {
        if (CollectionUtils.isEmpty(skuAttributeDTOList)) {
            return new ArrayList<>();
        }
        List<SkuAttributeVO> skuAttributeVOs = new ArrayList<>(skuAttributeDTOList.size());
        for (SkuAttributeDTO skuAttributeDTO : skuAttributeDTOList) {
            SkuAttributeVO skuAttributeVO = new SkuAttributeVO();
            skuAttributeVO.setId(skuAttributeDTO.getId());
            skuAttributeVO.setName(skuAttributeDTO.getName());
            skuAttributeVO.setAttrId(skuAttributeDTO.getAttributeId());
            String[] valueIdStrs = skuAttributeDTO.getValueListStr().split(",");
            List<Long> valueIds = Stream.of(valueIdStrs).map(Long::parseLong).toList();
            skuAttributeVO.setValueIds(valueIds);
            skuAttributeVOs.add(skuAttributeVO);
        }
        return skuAttributeVOs;
    }
}
