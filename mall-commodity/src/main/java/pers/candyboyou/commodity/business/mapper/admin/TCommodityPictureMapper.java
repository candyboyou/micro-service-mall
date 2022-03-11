package pers.candyboyou.commodity.business.mapper.admin;

import pers.candyboyou.commodity.business.model.dto.DescIdWithImgDTO;

import java.util.List;

public interface TCommodityPictureMapper {

    List<DescIdWithImgDTO> selectDescIdWithImgByIds(List<Long> descriptionIds);
}
