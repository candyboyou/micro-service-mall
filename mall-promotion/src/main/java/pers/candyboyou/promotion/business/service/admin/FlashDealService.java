package pers.candyboyou.mallpromotion.business.service.admin;

import io.candyboyou.common.framework.model.vo.ListVO;
import pers.candyboyou.mallpromotion.business.model.admin.param.FlashDealSaveParam;
import pers.candyboyou.mallpromotion.business.model.admin.param.FlashDealSearchParam;
import pers.candyboyou.mallpromotion.business.model.admin.vo.FlashDealDetailVO;
import pers.candyboyou.mallpromotion.business.model.admin.vo.FlashDealOfListVO;

public interface FlashDealService {

    /**
     * 根据参数分页查询秒杀列表
     */
    ListVO<FlashDealOfListVO> getFlashDealList(FlashDealSearchParam flashDealSearchParam);

    /**
     * 根据秒杀活动的id获取秒杀详情
     */
    FlashDealDetailVO getFlashDealDetail(Long flashDealId);

    /**
     * 保存或更新商品详情
     */
    void saveOrUpdateFlashDeal(FlashDealSaveParam flashDealSaveParam);
}
