package pers.candyboyou.commodity.business.enums;

import lombok.Getter;
import pers.candyboyou.commodity.business.model.vo.admin.OptionCommonVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public enum SelectTypeEnum {

    SINGLE_CHOICE(0, "单选"),
    MULTIPLE_CHOICE(1,"多选");

    private Integer id;

    private String name;

    SelectTypeEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<OptionCommonVO> getAllList() {
        SelectTypeEnum[] selectTypeEnums = SelectTypeEnum.values();
        List<OptionCommonVO> result = new ArrayList<>();
        for (SelectTypeEnum selectTypeEnum : selectTypeEnums) {
            OptionCommonVO commonVO = new OptionCommonVO();
            commonVO.setId(String.valueOf(selectTypeEnum.getId()));
            commonVO.setLabel(selectTypeEnum.getName());
            result.add(commonVO);
        }
        return result;
    }

    public static String getNameById(Integer id) {
        SelectTypeEnum[] selectTypeEnums = SelectTypeEnum.values();
        for (SelectTypeEnum selectTypeEnum : selectTypeEnums) {
            if (Objects.equals(selectTypeEnum.getId(), id)) {
                return selectTypeEnum.getName();
            }
        }
        return null;
    }
}
