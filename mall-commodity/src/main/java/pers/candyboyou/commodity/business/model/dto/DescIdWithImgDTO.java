package pers.candyboyou.commodity.business.model.dto;

import lombok.Data;

@Data
public class DescIdWithImgDTO {

    /**
     * 图片的id
     */
    private Long Id;

    /**
     * 图片的url
     */
    private String imgUrl;

    /**
     * 是否是主图
     */
    private Integer isMain;
}
