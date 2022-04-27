package pers.candyboyou.commodity.business.mapper.admin;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.candyboyou.commodity.business.model.dto.CategoryCascadeInfoDTO;
import pers.candyboyou.commodity.business.model.dto.CategoryDTO;
import pers.candyboyou.commodity.business.model.dto.CategoryNameDTO;
import pers.candyboyou.commodity.business.model.entity.CategoryEntity;
import pers.candyboyou.commodity.business.model.param.admin.CategorySearchParam;

import java.util.List;

@Repository
public interface TCommodityCategoryMapper {

    Long insertCategory(@Param("category") CategoryEntity categoryEntity);

    void deleteCategory(Long categoryId);

    void updateCategory(@Param("category") CategoryEntity categoryEntity);

    List<CategoryDTO> selectCategoriesBySearchParam(@Param("searchParam") CategorySearchParam searchParam);

    int selectCategoriesCountBySearchParam(@Param("searchParam") CategorySearchParam searchParam);

    String selectNameById(Long id);

    List<CategoryCascadeInfoDTO> selectCategoryIdWithNamesByIds(List<Long> categoryIds);

    List<CategoryCascadeInfoDTO> selectAllCategoryIdWithNames();

    List<CategoryNameDTO> getSubCategoryListById(Long id);

    List<CategoryNameDTO> getCategoryListByLevel(Integer level);

    CategoryEntity selectCategoryById(Long id);

    void updateCategorySort(@Param("sort") int sort, @Param("categoryId") Long categoryId);
}
