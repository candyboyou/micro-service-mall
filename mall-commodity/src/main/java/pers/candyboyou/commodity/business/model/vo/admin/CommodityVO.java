package pers.candyboyou.commodity.business.model.vo.admin;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CommodityVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3622175846076687164L;

    private Long id;

    private String name;

    private String img_url;

    private BigDecimal price;

    private Integer isPublish;

    private Integer isNew;

    private Integer isRecommend;

    private Integer skuId;

    private Integer spuSale;

    private Integer isVerify;


}
