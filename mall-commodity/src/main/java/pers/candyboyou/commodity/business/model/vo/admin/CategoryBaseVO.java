package pers.candyboyou.commodity.business.model.vo.admin;

import io.candyboyou.common.utils.CollectionUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.enums.LevelEnum;
import pers.candyboyou.commodity.business.enums.ShowStatusEnum;
import pers.candyboyou.commodity.business.enums.PlaceEnum;
import pers.candyboyou.commodity.business.model.dto.CategoryDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class CategoryBaseVO implements Serializable{

    @Serial
    private static final long serialVersionUID = -1460828958561201779L;

    @ApiModelProperty("分类ID")
    private Long id;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("上级分类ID")
    private Long parentId;

    @ApiModelProperty("上级分类名称")
    private String parentName;

    @ApiModelProperty("级别")
    private Integer levelId;

    private String level;

    @ApiModelProperty("分类的位置")
    private Integer placeId;

    private String place;

    @ApiModelProperty("显示状态：0->不显示；1->显示")
    private Integer showStatusId;

    private String showStatus;

    public static List<CategoryBaseVO> convertCategoryDTOList(List<CategoryDTO> categoryDTOList) {
        if (CollectionUtils.isEmpty(categoryDTOList)) {
            return new ArrayList<>();
        }
        HashMap<Long, String> idToNameMap = new HashMap<>();
        List<CategoryBaseVO> categoryBaseVOs = new ArrayList<>(categoryDTOList.size());
        for (CategoryDTO categoryDTO : categoryDTOList) {
            CategoryBaseVO categoryBaseVO = new CategoryBaseVO();
            categoryBaseVO.setId(categoryDTO.getId());
            categoryBaseVO.setName(categoryDTO.getName());
            categoryBaseVO.setParentId(categoryDTO.getParentId());
            Integer level = categoryDTO.getLevel();
            categoryBaseVO.setLevelId(level);
            categoryBaseVO.setLevel(LevelEnum.getNameById(level));
            Integer type = categoryDTO.getPlace();
            categoryBaseVO.setPlaceId(type);
            categoryBaseVO.setPlace(PlaceEnum.getNameById(type));
            Integer isShow = categoryDTO.getIsShow();
            categoryBaseVO.setShowStatusId(isShow);
            categoryBaseVO.setShowStatus(ShowStatusEnum.getNameById(isShow));
            categoryBaseVOs.add(categoryBaseVO);
            idToNameMap.put(categoryDTO.getId(), categoryDTO.getName());
        }
        for (CategoryBaseVO categoryBaseVO : categoryBaseVOs) {
            categoryBaseVO.setParentName(idToNameMap.get(categoryBaseVO.getId()));
        }
        return categoryBaseVOs;
    }
}
