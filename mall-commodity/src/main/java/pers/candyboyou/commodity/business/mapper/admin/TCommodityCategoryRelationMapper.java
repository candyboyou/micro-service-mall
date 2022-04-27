package pers.candyboyou.commodity.business.mapper.admin;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.candyboyou.commodity.business.model.dto.CategoryWithCommodityDTO;

import java.util.List;

@Repository
public interface TCommodityCategoryRelationMapper {

    void insertCategoryWithCommodity(@Param("categoryId") Long categoryId, @Param("commodityId") Long commodityId);

    void updateCategoryWithCommodity(@Param("id") Long id, @Param("commodityId") Long commodityId, @Param("categoryId") Long categoryId);

    List<CategoryWithCommodityDTO> batchSelectCommodityListByCategoryId(@Param("categoryIds") List<Long> categoryIds);
}
