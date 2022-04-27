package pers.candyboyou.commodity.business.enums;

import lombok.Getter;
import pers.candyboyou.commodity.business.model.vo.admin.OptionCommonVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public enum IsSaleEnum {

    NOT_SALE_ATTR(0, "否"),
    IS_SALE_ATTR(1,"是");

    private Integer id;

    private String name;

    IsSaleEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<OptionCommonVO> getAllList() {
        IsSaleEnum[] isSaleEnums = IsSaleEnum.values();
        List<OptionCommonVO> result = new ArrayList<>();
        for (IsSaleEnum isSaleEnum : isSaleEnums) {
            OptionCommonVO commonVO = new OptionCommonVO();
            commonVO.setId(String.valueOf(isSaleEnum.getId()));
            commonVO.setLabel(isSaleEnum.getName());
            result.add(commonVO);
        }
        return result;
    }

    public static String getNameById(Integer id) {
        IsSaleEnum[] isSaleEnums = IsSaleEnum.values();
        for (IsSaleEnum isSaleEnum : isSaleEnums) {
            if (Objects.equals(isSaleEnum.getId(), id)) {
                return isSaleEnum.getName();
            }
        }
        return null;
    }
}
