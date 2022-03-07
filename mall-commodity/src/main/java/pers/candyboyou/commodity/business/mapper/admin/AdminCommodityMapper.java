package pers.candyboyou.commodity.business.mapper.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import org.apache.ibatis.annotations.Param;
import pers.candyboyou.commodity.business.model.dto.CommodityOfListDTO;
import pers.candyboyou.commodity.business.model.dto.CommoditySaveDTO;
import pers.candyboyou.commodity.business.model.dto.SimpleCommodityDTO;
import pers.candyboyou.commodity.business.model.entity.CommodityEntity;
import pers.candyboyou.commodity.business.model.param.admin.CommoditySearchParam;
import pers.candyboyou.commodity.business.model.param.admin.CommodityStatusParam;

import java.util.List;

public interface AdminCommodityMapper {

    List<SimpleCommodityDTO> selectSimpleCommodityByCategoryId(Long categoryId, QueryParam queryParam);

    int selectSimpleCommodityCountsByCategoryId(Long categoryId, QueryParam queryParam);

    void deleteCategory(Long id);

    void updateCategoryOfCommodity(Long categoryId, Long commodityId);

    List<CommodityOfListDTO> selectCommodityOfList(@Param("param") CommoditySearchParam commoditySearchParam);

    int selectCommodityCounts(CommoditySearchParam commoditySearchParam);

    CommodityEntity selectCommodityById(Long id);

    void updateStatusOfCommodity(@Param("commodityStatusParam") CommodityStatusParam commodityStatusParam);

    Long saveCommodity(CommoditySaveDTO commoditySaveDTO);

    void updateCommodity(CommoditySaveDTO commoditySaveDTO);
}
