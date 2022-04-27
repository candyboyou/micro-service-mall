package pers.candyboyou.commodity.business.model.vo.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.model.dto.AttributeNameDTO;
import pers.candyboyou.commodity.business.model.dto.CategoryNameDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class OptionCommonVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 214628479285050749L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("名称")
    private String label;

    public static List<OptionCommonVO> convertAttributeNameDTOS(List<AttributeNameDTO> relationAttributeDTOS) {
        List<OptionCommonVO> optionCommonVOS = new ArrayList<>(relationAttributeDTOS.size());
        for (AttributeNameDTO relationAttributeDTO : relationAttributeDTOS) {
            OptionCommonVO optionCommonVO = new OptionCommonVO();
            optionCommonVO.setId(String.valueOf(relationAttributeDTO.getId()));
            optionCommonVO.setLabel(relationAttributeDTO.getName());
            optionCommonVOS.add(optionCommonVO);
        }
        return optionCommonVOS;
    }

    public static List<OptionCommonVO> convertCategoryNameDTO(List<CategoryNameDTO> categoryNameDTOS) {
        List<OptionCommonVO> optionCommonVOS = new ArrayList<>(categoryNameDTOS.size());
        for (CategoryNameDTO categoryNameDTO : categoryNameDTOS) {
            OptionCommonVO optionCommonVO = new OptionCommonVO();
            optionCommonVO.setId(String.valueOf(categoryNameDTO.getId()));
            optionCommonVO.setLabel(categoryNameDTO.getName());
            optionCommonVOS.add(optionCommonVO);
        }
        return optionCommonVOS;
    }
}
