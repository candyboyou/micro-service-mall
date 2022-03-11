package pers.candyboyou.commodity.business.service.admin;

import pers.candyboyou.commodity.business.model.vo.admin.ImgVO;

import java.util.List;
import java.util.Map;

public interface ImgService {

    Map<Long, ImgVO> getDescIdWithImgByIds(List<Long> descriptionIds);

    String getMainImgUrlByCommodityIds(List<Long> commodityIds);
}
