package pers.candyboyou.commodity.business.model.vo.admin;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class CategoryWithCommodityVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6787775174307213398L;

    /**
     * 分类id
     */
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 关联的商品
     */
    private List<OptionCommonVO> commodityList;

    private Integer sort;

}
