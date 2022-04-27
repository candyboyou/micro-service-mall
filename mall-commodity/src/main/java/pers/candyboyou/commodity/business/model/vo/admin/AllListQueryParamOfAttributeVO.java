package pers.candyboyou.commodity.business.model.vo.admin;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class AllListQueryParamOfAttributeVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4371793479887815827L;

    private List<OptionCommonVO> isSaleList;

}
