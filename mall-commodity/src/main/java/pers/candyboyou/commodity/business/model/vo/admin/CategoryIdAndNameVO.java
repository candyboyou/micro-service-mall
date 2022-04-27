package pers.candyboyou.commodity.business.model.vo.admin;

import io.candyboyou.common.utils.CollectionUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.model.dto.CategoryCascadeInfoDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class CategoryIdAndNameVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3729085753411394312L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("名称")
    private String label;

    @ApiModelProperty("子集")
    List<CategoryIdAndNameVO> children;

    public static List<CategoryIdAndNameVO> convertCategoryIdWithNameDTOS(List<CategoryCascadeInfoDTO> categoryIdWithNameDTOS) {

        HashMap<Long, ArrayList<CategoryCascadeInfoDTO>> parentIdToSubListMap = categoryIdWithNameDTOS.stream().collect(
                Collectors.groupingBy(CategoryCascadeInfoDTO::getParentId, HashMap::new, Collectors.toCollection(ArrayList::new))
        );

        return createCascadeList(parentIdToSubListMap);
    }

    private static List<CategoryIdAndNameVO> createCascadeList(HashMap<Long, ArrayList<CategoryCascadeInfoDTO>> parentIdToSubListMap) {
        List<CategoryIdAndNameVO> categoryIdAndNameVOS = new ArrayList<>();
        Set<Long> parentIds = parentIdToSubListMap.keySet();
        for (Long parentId : parentIds) {
            if (parentId != 0L) continue;
            ArrayList<CategoryCascadeInfoDTO> categoryCascadeInfoDTOS = parentIdToSubListMap.get(parentId);
            List<CategoryIdAndNameVO> subList = createSubList(categoryCascadeInfoDTOS, parentIdToSubListMap);
            if (CollectionUtils.isEmpty(subList)) continue;
            categoryIdAndNameVOS.addAll(subList);
        }
        return categoryIdAndNameVOS;
    }

    private static List<CategoryIdAndNameVO> createSubList(ArrayList<CategoryCascadeInfoDTO> categoryCascadeInfoDTOS, HashMap<Long, ArrayList<CategoryCascadeInfoDTO>> parentIdToSubListMap) {
        if (CollectionUtils.isEmpty(categoryCascadeInfoDTOS)) {
            return null;
        }
        List<CategoryIdAndNameVO> categoryIdAndNameVOS = new ArrayList<>(categoryCascadeInfoDTOS.size());
        for (CategoryCascadeInfoDTO categoryCascadeInfoDTO : categoryCascadeInfoDTOS) {
            Long id = categoryCascadeInfoDTO.getId();
            CategoryIdAndNameVO categoryIdAndNameVO = new CategoryIdAndNameVO();
            categoryIdAndNameVO.setId(id);
            categoryIdAndNameVO.setLabel(categoryCascadeInfoDTO.getName());
            List<CategoryIdAndNameVO> subList = createSubList(parentIdToSubListMap.get(id), parentIdToSubListMap);
            categoryIdAndNameVO.setChildren(subList);
            categoryIdAndNameVOS.add(categoryIdAndNameVO);
        }
        return categoryIdAndNameVOS;
    }
}
