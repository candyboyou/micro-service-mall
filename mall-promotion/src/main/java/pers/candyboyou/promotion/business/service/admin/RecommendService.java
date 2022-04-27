package pers.candyboyou.mallpromotion.business.service.admin;

import io.candyboyou.common.framework.model.vo.ListVO;
import pers.candyboyou.mallpromotion.business.model.admin.param.RecommendCommoditySaveParam;
import pers.candyboyou.mallpromotion.business.model.admin.param.RecommendCommoditySearchParam;
import pers.candyboyou.mallpromotion.business.model.admin.vo.RecommendCommodityVO;

import java.util.List;

public interface RecommendService {

    ListVO<RecommendCommodityVO> getRecommendCommodities(RecommendCommoditySearchParam recommendCommoditySearchParam);

    /**
     * 新增或修改秒杀商品
     */
    void saveOrUpdateRecommendCommodities(List<RecommendCommoditySaveParam> recommendCommoditySaveParams);
}
