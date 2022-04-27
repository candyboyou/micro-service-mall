package pers.candyboyou.mallpromotion.business.mapper.admin;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.candyboyou.mallpromotion.business.model.admin.dto.FlashDealCommodityDTO;
import pers.candyboyou.mallpromotion.business.model.admin.entity.FlashDealCommodityEntity;

import java.util.List;

@Repository
public interface TFlashDealCommodityMapper {

    void batchInsertFlashDealCommodities(@Param("flashDealCommodities") List<FlashDealCommodityDTO> flashDealCommodities, Long flashDealId);

    void batchUpdateFlashDealCommodities(@Param("flashDealCommodities") List<FlashDealCommodityDTO> flashDealCommodities);

    List<FlashDealCommodityEntity> selectFlashDealCommodityById(Long flashDealId);

    void batchDeleteFlashDealCommodities(@Param("commodityIds") List<Long> commodityIds);
}