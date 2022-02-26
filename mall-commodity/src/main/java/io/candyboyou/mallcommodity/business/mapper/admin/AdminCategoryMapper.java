package io.candyboyou.mallcommodity.business.mapper.admin;

import io.candyboyou.mallcommodity.business.model.dto.CategorySaveOrUpdateDTO;

public interface AdminCategoryMapper {

    void saveOrUpdateCategory(CategorySaveOrUpdateDTO categorySaveOrUpdateDTO);

}
