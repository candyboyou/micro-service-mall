package pers.candyboyou.mallpromotion.business.service.admin;

import pers.candyboyou.mallpromotion.business.model.admin.param.FlashDealCommodityParam;

import java.util.List;

public interface FlashDealCommodityService {

    /**
     * 新增秒杀商品
     */
    void saveFlashDealCommodities(List<FlashDealCommodityParam> flashDealCommodities, Long id);

    /**
     * 更新秒杀商品参数
     */
    void updateFlashDealCommodities(List<FlashDealCommodityParam> flashDealCommodities, Long flashDealId);
}
