package pers.candyboyou.mallpromotion.business.mapper.admin;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.candyboyou.mallpromotion.business.model.admin.dto.FlashDealSaveDTO;
import pers.candyboyou.mallpromotion.business.model.admin.entity.FlashDealEntity;
import pers.candyboyou.mallpromotion.business.model.admin.param.FlashDealSearchParam;

import java.util.List;

@Repository
public interface TFlashDealMapper {

    /**
     * 分页查询秒杀列表
     */
    List<FlashDealEntity> selectFlashDealsByParam(@Param("searchParam") FlashDealSearchParam flashDealSearchParam);

    /**
     * 根据查询参数查询总数量
     */
    int selectTotalCountByParam(@Param("searchParam") FlashDealSearchParam flashDealSearchParam);

    /**
     * 根据id查询
     */
    FlashDealEntity selectFlashDealById(Long flashDealId);

    /**
     * 查询秒杀信息
     * @param flashDealSaveDTO
     */
    Long insertFlashDeal(@Param("flashDealSaveDTO") FlashDealSaveDTO flashDealSaveDTO);

    /**
     * 更新秒杀信息
     */
    void updateFlashDeal(@Param("flashDealSaveDTO") FlashDealSaveDTO flashDealSaveDTO);
}
