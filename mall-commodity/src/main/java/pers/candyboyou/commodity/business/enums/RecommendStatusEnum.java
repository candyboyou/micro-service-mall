package pers.candyboyou.commodity.business.enums;

import lombok.Getter;
import pers.candyboyou.commodity.business.model.vo.admin.OptionCommonVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public enum RecommendStatusEnum {
    
    NOT_RECOMMEND(0, "否"),
    IS_RECOMMEND(1, "是");

    private Integer id;

    private String name;

    RecommendStatusEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<OptionCommonVO> getAllList() {
        RecommendStatusEnum[] RecommendStatusEnums = RecommendStatusEnum.values();
        List<OptionCommonVO> result = new ArrayList<>();
        for (RecommendStatusEnum RecommendStatusEnum : RecommendStatusEnums) {
            OptionCommonVO commonVO = new OptionCommonVO();
            commonVO.setId(String.valueOf(RecommendStatusEnum.getId()));
            commonVO.setLabel(RecommendStatusEnum.getName());
            result.add(commonVO);
        }
        return result;
    }

    public static String getNameById(Integer id) {
        RecommendStatusEnum[] recommendStatusEnums = RecommendStatusEnum.values();
        for (RecommendStatusEnum recommendStatusEnum : recommendStatusEnums) {
            if (Objects.equals(recommendStatusEnum.getId(), id)) {
                return recommendStatusEnum.getName();
            }
        }
        return null;
    }
}
