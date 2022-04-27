package pers.candyboyou.commodity.business.model.vo.admin;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class AllListParamOfCategoryVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -776965160652055616L;

    private List<OptionCommonVO> levelList;

    private List<OptionCommonVO> isShowList;

    private List<OptionCommonVO> placeList;

}
