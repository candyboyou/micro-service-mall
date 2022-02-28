package pers.candyboyou.commodity.business.mapper.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import pers.candyboyou.commodity.business.model.dto.SimpleCommodityDTO;

import java.util.List;

public interface AdminCommodityMapper {

    List<SimpleCommodityDTO> selectSimpleCommodityByCategoryId(Long categoryId, QueryParam queryParam);

    int selectSimpleCommodityCountsByCategoryId(Long categoryId, QueryParam queryParam);

    void deleteCategory(Long id);

    void updateCategoryOfCommodity(Long categoryId, Long commodityId);
}
