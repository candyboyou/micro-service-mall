package pers.candyboyou.commodity.business.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.candyboyou.commodity.business.model.dto.CategoryDTO;
import pers.candyboyou.commodity.business.model.dto.CategoryIdWithNameDTO;
import pers.candyboyou.commodity.business.model.dto.CategorySaveOrUpdateDTO;
import pers.candyboyou.commodity.business.model.param.admin.CategorySearchParam;

import java.util.List;

@Repository
public interface TCommodityCategoryMapper {

    void insertCategory(CategorySaveOrUpdateDTO categorySaveOrUpdateDTO);

    void deleteCategory(Long categoryId);

    void updateCategory(CategorySaveOrUpdateDTO categorySaveOrUpdateDTO);

    List<CategoryDTO> selectCategoriesBySearchParam(@Param("searchParam") CategorySearchParam searchParam);

    int selectCategoriesCountBySearchParam(CategorySearchParam searchParam);

    String selectNameById(Long id);

    List<CategoryIdWithNameDTO> selectCategoryIdWithNamesByIds(List<Long> categoryIds);
}
