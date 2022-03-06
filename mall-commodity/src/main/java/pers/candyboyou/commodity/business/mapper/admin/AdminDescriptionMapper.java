package pers.candyboyou.commodity.business.mapper.admin;

import pers.candyboyou.commodity.business.model.dto.DescIdWithImgDTO;

import java.util.List;

public interface AdminDescriptionMapper {

    List<DescIdWithImgDTO> selectDescIdWithImgByIds(List<Long> descriptionIds);
}
