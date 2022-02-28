package pers.candyboyou.commodity.business.model.param.admin;

import lombok.Data;

/**
 * 分类批量关联或删除商品参数对象
 */
@Data
public class RelateCategoryParam {

    private Long commodityId;

    private Long categoryId;

    private Integer isDelete = 0;

}
