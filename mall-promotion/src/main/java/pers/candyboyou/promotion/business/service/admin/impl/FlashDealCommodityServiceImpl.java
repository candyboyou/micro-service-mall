package pers.candyboyou.mallpromotion.business.service.admin.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.candyboyou.mallpromotion.business.mapper.admin.TFlashDealCommodityMapper;
import pers.candyboyou.mallpromotion.business.model.admin.dto.FlashDealCommodityDTO;
import pers.candyboyou.mallpromotion.business.model.admin.dto.FlashDealSaveDTO;
import pers.candyboyou.mallpromotion.business.model.admin.entity.FlashDealCommodityEntity;
import pers.candyboyou.mallpromotion.business.model.admin.param.FlashDealCommodityParam;
import pers.candyboyou.mallpromotion.business.service.admin.FlashDealCommodityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FlashDealCommodityServiceImpl implements FlashDealCommodityService {

    @Autowired
    private TFlashDealCommodityMapper flashDealCommodityMapper;

    @Override
    public void saveFlashDealCommodities(List<FlashDealCommodityParam> flashDealCommodities, Long flashDealId) {
        List<FlashDealCommodityDTO> flashDealCommodityDTOS = FlashDealCommodityDTO.convertFlashDealCommodities(flashDealCommodities);
        flashDealCommodityMapper.batchInsertFlashDealCommodities(flashDealCommodityDTOS, flashDealId);
    }

    @Override
    @Transactional
    public void updateFlashDealCommodities(List<FlashDealCommodityParam> flashDealCommodities, Long flashDealId) {
        List<FlashDealCommodityEntity> flashDealCommodityEntities = flashDealCommodityMapper.selectFlashDealCommodityById(flashDealId);
        Map<Long, FlashDealCommodityEntity> oldCommodities = flashDealCommodityEntities.stream().collect(Collectors.toMap(FlashDealCommodityEntity::getCommodityId, FlashDealCommodityEntity -> FlashDealCommodityEntity));

        List<FlashDealCommodityDTO> flashDealCommodityDTOS = FlashDealCommodityDTO.convertFlashDealCommodities(flashDealCommodities);
        Map<Long, FlashDealCommodityDTO> newCommodities = flashDealCommodityDTOS.stream().collect(Collectors.toMap(FlashDealCommodityDTO::getCommodityId, FlashDealCommodityDTO -> FlashDealCommodityDTO));

        List<FlashDealCommodityDTO> insertCommodities = new ArrayList<>();
        List<FlashDealCommodityDTO> updateCommodities = new ArrayList<>();
        List<Long> delCommodities = new ArrayList<>();

        for (Long commodityId : newCommodities.keySet()) {
            if (oldCommodities.containsKey(commodityId)) {
                updateCommodities.add(newCommodities.get(commodityId));
            } else {
                insertCommodities.add(newCommodities.get(commodityId));
            }
        }

        for (Long commodityId : oldCommodities.keySet()) {
            if (!newCommodities.containsKey(commodityId)) {
                delCommodities.add(commodityId);
            }
        }

        flashDealCommodityMapper.batchInsertFlashDealCommodities(insertCommodities, flashDealId);
        flashDealCommodityMapper.batchUpdateFlashDealCommodities(updateCommodities);
        flashDealCommodityMapper.batchDeleteFlashDealCommodities(delCommodities);
    }
}
