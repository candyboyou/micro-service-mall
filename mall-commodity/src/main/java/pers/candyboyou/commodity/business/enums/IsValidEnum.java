package pers.candyboyou.commodity.business.enums;

import lombok.Getter;
import pers.candyboyou.commodity.business.model.vo.admin.OptionCommonVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public enum IsValidEnum {

    VALID(1,"启用"),
    NOT_VALID(0, "禁用");

    private Integer id;

    private String name;

    IsValidEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<OptionCommonVO> getAllList() {
        IsValidEnum[] isValidEnums = IsValidEnum.values();
        List<OptionCommonVO> result = new ArrayList<>();
        for (IsValidEnum isValidEnum : isValidEnums) {
            OptionCommonVO commonVO = new OptionCommonVO();
            commonVO.setId(String.valueOf(isValidEnum.getId()));
            commonVO.setLabel(isValidEnum.getName());
            result.add(commonVO);
        }
        return result;
    }

    public static String getNameById(Integer id) {
        IsValidEnum[] isValidEnums = IsValidEnum.values();
        for (IsValidEnum isValidEnum : isValidEnums) {
            if (Objects.equals(isValidEnum.getId(), id)) {
                return isValidEnum.getName();
            }
        }
        return null;
    }
}
