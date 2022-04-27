package pers.candyboyou.commodity.business.enums;

import lombok.Getter;
import pers.candyboyou.commodity.business.model.vo.admin.OptionCommonVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public enum PublishStatusEnum {

    NOT_PUBLISH(0, "未上线"),
    IS_PUBLISH(1, "已上线");

    private Integer id;

    private String name;

    PublishStatusEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<OptionCommonVO> getAllList() {
        PublishStatusEnum[] publishStatusEnums = PublishStatusEnum.values();
        List<OptionCommonVO> result = new ArrayList<>();
        for (PublishStatusEnum publishStatusEnum : publishStatusEnums) {
            OptionCommonVO commonVO = new OptionCommonVO();
            commonVO.setId(String.valueOf(publishStatusEnum.getId()));
            commonVO.setLabel(publishStatusEnum.getName());
            result.add(commonVO);
        }
        return result;
    }

    public static String getNameById(Integer id) {
        PublishStatusEnum[] publishStatusEnums = PublishStatusEnum.values();
        for (PublishStatusEnum publishStatusEnum : publishStatusEnums) {
            if (Objects.equals(publishStatusEnum.getId(), id)) {
                return publishStatusEnum.getName();
            }
        }
        return null;
    }
}
