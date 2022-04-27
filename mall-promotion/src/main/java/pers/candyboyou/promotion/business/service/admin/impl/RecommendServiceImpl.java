package pers.candyboyou.mallpromotion.business.service.admin.impl;

import io.candyboyou.common.framework.model.vo.ListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.mallpromotion.business.mapper.admin.THomeRecommendCommodityMapper;
import pers.candyboyou.mallpromotion.business.model.admin.entity.RecommendCommodityEntity;
import pers.candyboyou.mallpromotion.business.model.admin.param.RecommendCommoditySaveParam;
import pers.candyboyou.mallpromotion.business.model.admin.param.RecommendCommoditySearchParam;
import pers.candyboyou.mallpromotion.business.model.admin.vo.RecommendCommodityVO;
import pers.candyboyou.mallpromotion.business.service.admin.RecommendService;

import java.util.List;

@Service
@Slf4j
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private THomeRecommendCommodityMapper tHomeRecommendCommodityMapper;

    @Override
    public ListVO<RecommendCommodityVO> getRecommendCommodities(RecommendCommoditySearchParam recommendCommoditySearchParam) {
        List<RecommendCommodityEntity> recommendCommodityEntities = tHomeRecommendCommodityMapper.selectRecommendCommodityEntitiesByParam(recommendCommoditySearchParam);
        List<RecommendCommodityVO> recommendCommodityVOs = RecommendCommodityVO.convertRecommendCommodityEntities(recommendCommodityEntities);
        int totalCount = tHomeRecommendCommodityMapper.selectCountOfRecommendCommodityByParam(recommendCommoditySearchParam);
        ListVO<RecommendCommodityVO> recommendCommodityVOListVO = new ListVO<>();
        recommendCommodityVOListVO.setList(recommendCommodityVOs);
        recommendCommodityVOListVO.setTotal(totalCount);
        recommendCommodityVOListVO.setPageNum(recommendCommoditySearchParam.getPageNum());
        recommendCommodityVOListVO.setPageSize(recommendCommoditySearchParam.getPageSize());
        return recommendCommodityVOListVO;
    }

    @Override
    public void saveOrUpdateRecommendCommodities(List<RecommendCommoditySaveParam> recommendCommoditySaveParams) {

    }
}
