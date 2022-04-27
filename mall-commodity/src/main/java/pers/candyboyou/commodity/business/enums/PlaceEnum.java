package pers.candyboyou.commodity.business.enums;

import lombok.Getter;
import pers.candyboyou.commodity.business.model.vo.admin.OptionCommonVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public enum PlaceEnum {

    DEFAULT(0, "默认"),
    UP_INDEX(1, "首页上方"),
    DOWN_INDEX(2, "首页下方");

    private Integer id;

    private String name;

    PlaceEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<OptionCommonVO> getAllList() {
        PlaceEnum[] typeOfCategoryEnums = PlaceEnum.values();
        List<OptionCommonVO> result = new ArrayList<>();
        for (PlaceEnum typeOfCategoryEnum : typeOfCategoryEnums) {
            OptionCommonVO commonVO = new OptionCommonVO();
            commonVO.setId(String.valueOf(typeOfCategoryEnum.getId()));
            commonVO.setLabel(typeOfCategoryEnum.getName());
            result.add(commonVO);
        }
        return result;
    }

    public static String getNameById(Integer id) {
        PlaceEnum[] typeOfCategoryEnums = PlaceEnum.values();
        for (PlaceEnum typeOfCategoryEnum : typeOfCategoryEnums) {
            if (Objects.equals(typeOfCategoryEnum.getId(), id)) {
                return typeOfCategoryEnum.getName();
            }
        }
        return null;
    }
}
