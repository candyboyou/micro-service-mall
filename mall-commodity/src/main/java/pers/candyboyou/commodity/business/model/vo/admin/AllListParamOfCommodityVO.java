package pers.candyboyou.commodity.business.model.vo.admin;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class AllListParamOfCommodityVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5453685295774850668L;

    private List<CategoryIdAndNameVO> categoryList;

    private List<OptionCommonVO> newStatusList;

    private List<OptionCommonVO> publishStatusList;

    private List<OptionCommonVO> recommendStatusList;

    private List<OptionCommonVO> verifyStatusList;

}
