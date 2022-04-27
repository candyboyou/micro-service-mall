package pers.candyboyou.commodity.business.enums;

import lombok.Getter;
import pers.candyboyou.commodity.business.model.vo.admin.OptionCommonVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public enum LevelEnum {

    ONE(0, "一级分类"),
    TWO(1, "二级分类"),
    THREE(2, "三级分类");

    private Integer id;

    private String name;

    LevelEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<OptionCommonVO> getAllList() {
        LevelEnum[] levelEnums = LevelEnum.values();
        List<OptionCommonVO> result = new ArrayList<>();
        for (LevelEnum levelEnum : levelEnums) {
            OptionCommonVO commonVO = new OptionCommonVO();
            commonVO.setId(String.valueOf(levelEnum.getId()));
            commonVO.setLabel(levelEnum.getName());
            result.add(commonVO);
        }
        return result;
    }

    public static String getNameById(Integer id) {
        LevelEnum[] levelEnums = LevelEnum.values();
        for (LevelEnum levelEnum : levelEnums) {
            if (Objects.equals(levelEnum.getId(), id)) {
                return levelEnum.getName();
            }
        }
        return null;
    }
}
