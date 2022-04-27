package pers.candyboyou.commodity.business.enums;

import lombok.Getter;
import pers.candyboyou.commodity.business.model.vo.admin.OptionCommonVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public enum InputTypeEnum {

    MANUAL_INPUT(0, "手动输入"),
    SELECT_OPTION(1,"选择预置值");

    private Integer id;

    private String name;

    InputTypeEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<OptionCommonVO> getAllList() {
        InputTypeEnum[] inputTypeEnums = InputTypeEnum.values();
        List<OptionCommonVO> result = new ArrayList<>();
        for (InputTypeEnum inputTypeEnum : inputTypeEnums) {
            OptionCommonVO commonVO = new OptionCommonVO();
            commonVO.setId(String.valueOf(inputTypeEnum.getId()));
            commonVO.setLabel(inputTypeEnum.getName());
            result.add(commonVO);
        }
        return result;
    }

    public static String getNameById(Integer id) {
        InputTypeEnum[] inputTypeEnums = InputTypeEnum.values();
        for (InputTypeEnum inputTypeEnum : inputTypeEnums) {
            if (Objects.equals(inputTypeEnum.getId(), id)) {
                return inputTypeEnum.getName();
            }
        }
        return null;
    }
}
