package pers.candyboyou.commodity.business.service.admin.impl;

import io.candyboyou.common.framework.model.vo.ListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.commodity.business.mapper.admin.AdminCommodityMapper;
import pers.candyboyou.commodity.business.model.param.admin.CommoditySaveParam;
import pers.candyboyou.commodity.business.model.param.admin.CommoditySearchParam;
import pers.candyboyou.commodity.business.model.vo.admin.CommodityDetailVO;
import pers.candyboyou.commodity.business.model.vo.admin.CommodityVO;
import pers.candyboyou.commodity.business.service.admin.AdminCommodityService;

import java.util.List;

@Service
@Slf4j
public class AdminCommodityServiceImpl implements AdminCommodityService {

    @Autowired
    private AdminCommodityMapper adminCommodityMapper;

    @Override
    public ListVO<CommodityVO> getCommodityVOS(CommoditySearchParam commoditySearchParam) {

        return null;
    }

    @Override
    public CommodityDetailVO getCommodityDetailVO(Long id) {
        return null;
    }

    @Override
    public void saveOrUpdateCommodity(List<CommoditySaveParam> commoditySaveParams) {

    }
}
