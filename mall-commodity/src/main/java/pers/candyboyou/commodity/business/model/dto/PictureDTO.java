package pers.candyboyou.commodity.business.model.dto;

import lombok.Data;

@Data
public class PictureDTO {

    /**
     * 图片的id
     */
    private Long id;

    /**
     * 图片的id
     */
    private Long commodityId;

    /**
     * 图片的url
     */
    private String pictureUrl;

    /**
     * 是否是主图
     */
    private Integer isMain;
}
