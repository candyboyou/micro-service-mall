package pers.candyboyou.commodity.business.service.admin;

import io.candyboyou.common.framework.model.vo.ListVO;
import pers.candyboyou.commodity.business.model.param.admin.CommoditySaveParam;
import pers.candyboyou.commodity.business.model.param.admin.CommoditySearchParam;
import pers.candyboyou.commodity.business.model.vo.CommodityDetailVO;
import pers.candyboyou.commodity.business.model.vo.admin.CommodityVO;

import java.util.List;

public interface AdminCommodityService {

    ListVO<CommodityVO> getCommodityVOS(CommoditySearchParam commoditySearchParam);

    CommodityDetailVO getCommodityDetailVO(Long id);

    void saveOrUpdateCommodity(List<CommoditySaveParam> commoditySaveParams);
}
