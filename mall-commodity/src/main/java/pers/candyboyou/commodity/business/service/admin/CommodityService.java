package pers.candyboyou.commodity.business.service.admin;

import io.candyboyou.common.framework.model.vo.ListVO;
import pers.candyboyou.commodity.business.model.param.admin.CommoditySaveParam;
import pers.candyboyou.commodity.business.model.param.admin.CommoditySearchParam;
import pers.candyboyou.commodity.business.model.param.admin.CommodityStatusParam;
import pers.candyboyou.commodity.business.model.vo.admin.CommodityDetailVO;
import pers.candyboyou.commodity.business.model.vo.admin.CommodityOfListVO;

import java.util.List;

public interface CommodityService {

    /**
     * 查询获取商品列表
     */
    ListVO<CommodityOfListVO> getCommodityVOS(CommoditySearchParam commoditySearchParam);

    /**
     * 获取商品的详细信息
     */
    CommodityDetailVO getCommodityDetailVO(Long id);

    /**
     * 保存或更新商品详细信息
     */
    void saveOrUpdateCommodity(CommoditySaveParam commoditySaveParams);

    /**
     * 在列表上更新商品的状态
     */
    void updateCommodityStatus(CommodityStatusParam commodityStatusParam);
}
