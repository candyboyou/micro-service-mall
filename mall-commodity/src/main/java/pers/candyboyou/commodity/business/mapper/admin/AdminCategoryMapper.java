package pers.candyboyou.commodity.business.mapper.admin;

import org.apache.ibatis.annotations.Param;
import pers.candyboyou.commodity.business.model.dto.CategoryDTO;
import pers.candyboyou.commodity.business.model.dto.CategorySaveOrUpdateDTO;
import pers.candyboyou.commodity.business.model.dto.SimpleCommodityDTO;
import pers.candyboyou.commodity.business.model.param.admin.CategorySearchParam;
import pers.candyboyou.commodity.business.model.vo.admin.CategoryVO;

import java.util.List;

public interface AdminCategoryMapper {

    void insertCategory(CategorySaveOrUpdateDTO categorySaveOrUpdateDTO);

    void deleteCategory(Long categoryId);

    void updateCategory(CategorySaveOrUpdateDTO categorySaveOrUpdateDTO);

    List<CategoryDTO> selectCategoriesBySearchParam(@Param("searchParam") CategorySearchParam searchParam);

    int selectCategoriesCountBySearchParam(CategorySearchParam searchParam);

}
