package pers.candyboyou.commodity.business.enums;

import lombok.Getter;
import pers.candyboyou.commodity.business.model.vo.admin.OptionCommonVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public enum NewStatusEnum {

    NOT_NEW(0, "否"),
    IS_NEW(1, "是");

    private Integer id;

    private String name;

    NewStatusEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<OptionCommonVO> getAllList() {
        NewStatusEnum[] newStatusEnums = NewStatusEnum.values();
        List<OptionCommonVO> result = new ArrayList<>();
        for (NewStatusEnum newStatusEnum : newStatusEnums) {
            OptionCommonVO commonVO = new OptionCommonVO();
            commonVO.setId(String.valueOf(newStatusEnum.getId()));
            commonVO.setLabel(newStatusEnum.getName());
            result.add(commonVO);
        }
        return result;
    }

    public static String getNameById(Integer id) {
        NewStatusEnum[] newStatusEnums = NewStatusEnum.values();
        for (NewStatusEnum newStatusEnum : newStatusEnums) {
            if (Objects.equals(newStatusEnum.getId(), id)) {
                return newStatusEnum.getName();
            }
        }
        return null;
    }
}
