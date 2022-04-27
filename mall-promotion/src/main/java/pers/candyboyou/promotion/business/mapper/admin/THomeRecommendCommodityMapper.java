package pers.candyboyou.mallpromotion.business.mapper.admin;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.candyboyou.mallpromotion.business.model.admin.entity.RecommendCommodityEntity;
import pers.candyboyou.mallpromotion.business.model.admin.param.RecommendCommoditySearchParam;

import java.util.List;

@Repository
public interface THomeRecommendCommodityMapper {

    List<RecommendCommodityEntity> selectRecommendCommodityEntitiesByParam(@Param("searchParam") RecommendCommoditySearchParam recommendCommoditySearchParam);

    int selectCountOfRecommendCommodityByParam(@Param("searchParam") RecommendCommoditySearchParam recommendCommoditySearchParam);
}
