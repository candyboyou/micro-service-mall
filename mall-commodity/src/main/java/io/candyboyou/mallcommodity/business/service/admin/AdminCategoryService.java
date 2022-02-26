package io.candyboyou.mallcommodity.business.service.admin;

import io.candyboyou.mallcommodity.business.model.param.admin.CategorySaveOrUpdateParam;

public interface AdminCategoryService {

    void saveOrUpdateCategory(CategorySaveOrUpdateParam categorySaveParam);

}
