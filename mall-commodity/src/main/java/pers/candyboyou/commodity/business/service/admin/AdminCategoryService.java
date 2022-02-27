package pers.candyboyou.commodity.business.service.admin;

import io.candyboyou.common.framework.model.vo.ListVO;
import pers.candyboyou.commodity.business.model.param.admin.CategorySaveOrUpdateParam;
import pers.candyboyou.commodity.business.model.param.admin.CategorySearchParam;
import pers.candyboyou.commodity.business.model.param.admin.RelateCategoryParam;
import pers.candyboyou.commodity.business.model.vo.admin.CategoryVO;
import pers.candyboyou.commodity.business.model.vo.admin.SimpleCommodityInfoVO;

public interface AdminCategoryService {

    void saveOrUpdateCategory(CategorySaveOrUpdateParam categorySaveParam);

    ListVO<CategoryVO> getCategories(CategorySearchParam searchParam);

    ListVO<SimpleCommodityInfoVO> getSimpleCommodityInfos(Long categoryId);

    void relateCommodities(RelateCategoryParam relateCategoryParam);
}
