package pers.candyboyou.commodity.business.enums;

import lombok.Getter;
import pers.candyboyou.commodity.business.model.vo.admin.OptionCommonVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public enum VerifyStatusEnum {

    NOT_VERIFIED(0, "未审核"),
    PASSED(1, "已通过"),
    DENIED(2, "未通过");

    private Integer id;

    private String name;

    VerifyStatusEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<OptionCommonVO> getAllList() {
        VerifyStatusEnum[] verifyStatusEnums = VerifyStatusEnum.values();
        List<OptionCommonVO> result = new ArrayList<>();
        for (VerifyStatusEnum verifyStatusEnum : verifyStatusEnums) {
            OptionCommonVO commonVO = new OptionCommonVO();
            commonVO.setId(String.valueOf(verifyStatusEnum.getId()));
            commonVO.setLabel(verifyStatusEnum.getName());
            result.add(commonVO);
        }
        return result;
    }

    public static String getNameById(Integer id) {
        VerifyStatusEnum[] verifyStatusEnums = VerifyStatusEnum.values();
        for (VerifyStatusEnum verifyStatusEnum : verifyStatusEnums) {
            if (Objects.equals(verifyStatusEnum.getId(), id)) {
                return verifyStatusEnum.getName();
            }
        }
        return null;
    }
}
