package pers.candyboyou.commodity.business.model.vo.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AllListSaveParamOfAttributeVO extends AllListQueryParamOfAttributeVO {

    @Serial
    private static final long serialVersionUID = 3673336738361118625L;

    private List<OptionCommonVO> inputTypeList;

    private List<OptionCommonVO> isValidList;

    private List<OptionCommonVO> selectTypeList;

    private List<OptionCommonVO> searchTypeList;



}
