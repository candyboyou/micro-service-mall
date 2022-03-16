package pers.candyboyou.commodity.business.model.vo.admin;

import io.candyboyou.common.utils.CollectionUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.model.dto.PictureDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class PictureVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 976551465821440290L;

    @ApiModelProperty("图片的id")
    private Long id;

    @ApiModelProperty("图片URL")
    private String pictureUrl;

    @ApiModelProperty("是否是主图")
    private Integer isMain;

    public static PictureVO convertPictureDTO(PictureDTO pictureDTO) {
        PictureVO pictureVO = new PictureVO();
        pictureVO.setId(pictureDTO.getCommodityId());
        pictureVO.setPictureUrl(pictureDTO.getPictureUrl());
        pictureVO.setIsMain(pictureDTO.getIsMain());
        return pictureVO;
    }

    public static List<PictureVO> convertPictureDTOs(List<PictureDTO> pictureDTOs) {
        if (CollectionUtils.isEmpty(pictureDTOs)) {
            return null;
        }
        List<PictureVO> pictureVOs = new ArrayList<>();
        for (PictureDTO pictureDTO : pictureDTOs) {
            PictureVO pictureVO = new PictureVO();
            pictureVO.setId(pictureDTO.getId());
            pictureVO.setPictureUrl(pictureDTO.getPictureUrl());
            pictureVO.setIsMain(pictureDTO.getIsMain());
            pictureVOs.add(pictureVO);
        }
        return pictureVOs;
    }
}
