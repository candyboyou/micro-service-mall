package pers.candyboyou.commodity.business.mapper.admin;

import org.springframework.stereotype.Repository;
import pers.candyboyou.commodity.business.model.dto.DescIdWithImgDTO;

import java.util.List;

@Repository
public interface TCommodityPictureMapper {

    List<DescIdWithImgDTO> selectDescIdWithImgByIds(List<Long> descriptionIds);

    List<String> selectMainImgByCommodityIds(List<Long> commodityIds);

}
