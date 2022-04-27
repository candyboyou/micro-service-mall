package pers.candyboyou.mallpromotion.business.service.admin.impl;

import io.candyboyou.common.expection.BusinessException;
import io.candyboyou.common.framework.model.vo.ListVO;
import io.candyboyou.common.utils.CollectionUtils;
import io.candyboyou.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pers.candyboyou.mallpromotion.business.mapper.admin.TFlashDealMapper;
import pers.candyboyou.mallpromotion.business.model.admin.dto.FlashDealSaveDTO;
import pers.candyboyou.mallpromotion.business.model.admin.entity.FlashDealEntity;
import pers.candyboyou.mallpromotion.business.model.admin.param.FlashDealCommodityParam;
import pers.candyboyou.mallpromotion.business.model.admin.param.FlashDealSaveParam;
import pers.candyboyou.mallpromotion.business.model.admin.param.FlashDealSearchParam;
import pers.candyboyou.mallpromotion.business.model.admin.vo.FlashDealDetailVO;
import pers.candyboyou.mallpromotion.business.model.admin.vo.FlashDealOfListVO;
import pers.candyboyou.mallpromotion.business.service.admin.FlashDealCommodityService;
import pers.candyboyou.mallpromotion.business.service.admin.FlashDealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FlashDealServiceImpl implements FlashDealService {

    @Autowired
    private TFlashDealMapper tFlashDealMapper;

    @Autowired
    private FlashDealCommodityService flashDealCommodityService;

    @Override
    public ListVO<FlashDealOfListVO> getFlashDealList(FlashDealSearchParam flashDealSearchParam) {
        List<FlashDealEntity> flashDealEntities = tFlashDealMapper.selectFlashDealsByParam(flashDealSearchParam);
        List<FlashDealOfListVO> flashDealOfListVOS = FlashDealOfListVO.convertFlashDealEntities(flashDealEntities);
        int totalCount = tFlashDealMapper.selectTotalCountByParam(flashDealSearchParam);
        ListVO<FlashDealOfListVO> flashDealEntityListVO = new ListVO<>();
        flashDealEntityListVO.setList(flashDealOfListVOS);
        flashDealEntityListVO.setTotal(totalCount);
        flashDealEntityListVO.setPageNum(flashDealSearchParam.getPageNum());
        flashDealEntityListVO.setPageSize(flashDealSearchParam.getPageSize());
        return flashDealEntityListVO;
    }

    @Override
    public FlashDealDetailVO getFlashDealDetail(Long flashDealId) {
        FlashDealEntity flashDealEntity = tFlashDealMapper.selectFlashDealById(flashDealId);
        FlashDealDetailVO flashDealDetailVO = FlashDealDetailVO.convertFlashDealEntity(flashDealEntity);
        // TODO 获取商品的名称
        return flashDealDetailVO;
    }

    @Override
    @Transactional
    public void saveOrUpdateFlashDeal(FlashDealSaveParam flashDealSaveParam) {
        FlashDealSaveDTO flashDealSaveDTO = checkFlashDealInfo(flashDealSaveParam);
        List<FlashDealCommodityParam> flashDealCommodities = flashDealSaveParam.getFlashDealCommodities();
        if (flashDealSaveParam.getId() == null) {
            Long flashDealId = tFlashDealMapper.insertFlashDeal(flashDealSaveDTO);
            flashDealCommodityService.saveFlashDealCommodities(flashDealCommodities, flashDealId);
        } else {
            tFlashDealMapper.updateFlashDeal(flashDealSaveDTO);
            flashDealCommodityService.updateFlashDealCommodities(flashDealCommodities, flashDealSaveParam.getId());
        }
    }

    private FlashDealSaveDTO checkFlashDealInfo(FlashDealSaveParam flashDealSaveParam) {
        String name = flashDealSaveParam.getName();
        if (StringUtils.isBlank(name)) {
            throw new BusinessException("秒杀活动名称不能为空");
        }
        Long startTime = flashDealSaveParam.getStartTime();
        if (startTime == null) {
            throw new BusinessException("秒杀开始时间不能为空");
        }
        Long endTime = flashDealSaveParam.getEndTime();
        if (endTime == null) {
            throw new BusinessException("秒杀截止时间不能为空");
        }
        List<FlashDealCommodityParam> flashDealCommodities = flashDealSaveParam.getFlashDealCommodities();
        if (CollectionUtils.isEmpty(flashDealCommodities)) {
            throw new BusinessException("秒杀商品不能为空");
        }
        FlashDealSaveDTO flashDealSaveDTO = new FlashDealSaveDTO();
        flashDealSaveDTO.setName(name);
        flashDealSaveDTO.setStartTime(new Date(startTime));
        flashDealSaveDTO.setEndTime(new Date(endTime));
        flashDealSaveDTO.setStatus(flashDealSaveParam.getStatus());
        flashDealSaveDTO.setIsOnline(flashDealSaveParam.getIsOnline());
        return flashDealSaveDTO;
    }

}
