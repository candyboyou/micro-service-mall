package pers.candyboyou.commodity.business.model.vo.admin;

import io.candyboyou.common.framework.model.vo.ListVO;
import io.candyboyou.common.utils.CollectionUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Value;
import pers.candyboyou.commodity.business.model.dto.CategoryDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryVO implements Serializable{

    @Serial
    private static final long serialVersionUID = -1460828958561201779L;

    @ApiModelProperty("分类ID")
    private Long categoryId;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("级别")
    private Integer level;

    @ApiModelProperty("上级分类ID")
    private Integer parentId;

    @ApiModelProperty("商品数量")
    private String count;

    @ApiModelProperty("数量单位ID")
    private Integer unitId;

    @ApiModelProperty("显示状态：0->不显示；1->显示")
    private Integer isShow;

    @ApiModelProperty("分类描述")
    private String description;

    public static List<CategoryVO> convertCategoryDTOList(List<CategoryDTO> categoryDTOList) {
        if (CollectionUtils.isEmpty(categoryDTOList)) {
            return new ArrayList<>();
        }
        List<CategoryVO> categoryVOs = new ArrayList<>(categoryDTOList.size());
        for (CategoryDTO categoryDTO : categoryDTOList) {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setCategoryId(categoryDTO.getId());
            categoryVO.setName(categoryDTO.getName());
            categoryVO.setLevel(categoryDTO.getLevel());
            categoryVO.setParentId(categoryDTO.getParentId());
            categoryVO.setCount(categoryDTO.getCount());
            categoryVO.setUnitId(categoryDTO.getUnitId());
            categoryVO.setIsShow(categoryDTO.getIsShow());
            categoryVO.setDescription(categoryDTO.getDescription());
            categoryVOs.add(categoryVO);
        }
        return categoryVOs;
    }
}
