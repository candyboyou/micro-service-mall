package pers.candyboyou.commodity.business.service.admin;

import io.candyboyou.common.framework.model.vo.PageResult;
import pers.candyboyou.commodity.business.model.param.admin.CommoditySaveParam;
import pers.candyboyou.commodity.business.model.param.admin.CommoditySearchParam;
import pers.candyboyou.commodity.business.model.param.admin.CommodityStatusParam;
import pers.candyboyou.commodity.business.model.param.admin.CommodityUpdateParam;
import pers.candyboyou.commodity.business.model.vo.admin.AllListParamOfCommodityVO;
import pers.candyboyou.commodity.business.model.vo.admin.CommodityDetailVO;
import pers.candyboyou.commodity.business.model.vo.admin.CommodityOfListVO;

import java.util.List;

public interface CommodityService {

    /**
     * 查询获取商品列表
     */
    PageResult<CommodityOfListVO> paginateGetCommodities(CommoditySearchParam commoditySearchParam);

    /**
     * 获取商品的详细信息
     */
    CommodityDetailVO getCommodityDetailVO(Long id);

    /**
     * 保存或更新商品详细信息
     */
    Long newCommodity(CommoditySaveParam commoditySaveParams);

    /**
     * 更新商品的信息
     */
    void updateCommodity(CommodityUpdateParam commodityUpdateParam);

    /**
     * 在列表上更新商品的状态
     */
    void updateCommodityStatus(CommodityStatusParam commodityStatusParam);

    AllListParamOfCommodityVO getAllParamList();
}
