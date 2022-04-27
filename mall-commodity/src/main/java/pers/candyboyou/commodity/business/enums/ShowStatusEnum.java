package pers.candyboyou.commodity.business.enums;

import lombok.Getter;
import pers.candyboyou.commodity.business.model.vo.admin.OptionCommonVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public enum ShowStatusEnum {

    SHOW(1, "启用"),
    NOT_SHOW(0, "禁用");

    private Integer id;

    private String name;

    ShowStatusEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<OptionCommonVO> getAllList() {
        ShowStatusEnum[] showStatusEnums = ShowStatusEnum.values();
        List<OptionCommonVO> result = new ArrayList<>();
        for (ShowStatusEnum showStatusEnum: showStatusEnums) {
            OptionCommonVO commonVO = new OptionCommonVO();
            commonVO.setId(String.valueOf(showStatusEnum.getId()));
            commonVO.setLabel(showStatusEnum.getName());
            result.add(commonVO);
        }
        return result;
    }

    public static String getNameById(Integer id) {
        ShowStatusEnum[] showStatusEnums = ShowStatusEnum.values();
        for (ShowStatusEnum showStatusEnum : showStatusEnums) {
            if (Objects.equals(showStatusEnum.getId(), id)) {
                return showStatusEnum.getName();
            }
        }
        return null;
    }
}
