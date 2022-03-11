package pers.candyboyou.commodity.business.model.vo.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.model.dto.DescIdWithImgDTO;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ImgVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 976551465821440290L;

    @ApiModelProperty("图片的id")
    private Long id;

    @ApiModelProperty("图片URL")
    private String imgUrl;

    @ApiModelProperty("是否是主图")
    private Integer isMain;

    public static ImgVO convertImgDTO(DescIdWithImgDTO imgDTO) {
        ImgVO imgVO = new ImgVO();
        imgVO.setId(imgDTO.getId());
        imgVO.setImgUrl(imgDTO.getImgUrl());
        imgVO.setIsMain(imgDTO.getIsMain());
        return imgVO;
    }
}
