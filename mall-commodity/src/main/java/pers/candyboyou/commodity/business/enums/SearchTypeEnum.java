package pers.candyboyou.commodity.business.enums;

import lombok.Getter;
import pers.candyboyou.commodity.business.model.vo.admin.OptionCommonVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public enum SearchTypeEnum {

    SUPPORT_RETRIEVAL(0, "不支持检索"),
    NOT_SUPPORT_RETRIEVAL(1, "支持检索");

    private Integer id;

    private String name;

    SearchTypeEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<OptionCommonVO> getAllList() {
        SearchTypeEnum[] searchTypeEnums = SearchTypeEnum.values();
        List<OptionCommonVO> result = new ArrayList<>();
        for (SearchTypeEnum searchTypeEnum : searchTypeEnums) {
            OptionCommonVO commonVO = new OptionCommonVO();
            commonVO.setId(String.valueOf(searchTypeEnum.getId()));
            commonVO.setLabel(searchTypeEnum.getName());
            result.add(commonVO);
        }
        return result;
    }

    public static String getNameById(Integer id) {
        SearchTypeEnum[] searchTypeEnums = SearchTypeEnum.values();
        for (SearchTypeEnum searchTypeEnum : searchTypeEnums) {
            if (Objects.equals(searchTypeEnum.getId(), id)) {
                return searchTypeEnum.getName();
            }
        }
        return null;
    }

}
