package pers.candyboyou.commodity.business.service.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.candyboyou.common.framework.model.vo.PageResult;
import pers.candyboyou.commodity.business.model.dto.AttributeNameDTO;
import pers.candyboyou.commodity.business.model.param.admin.CategorySaveOrUpdateParam;
import pers.candyboyou.commodity.business.model.param.admin.CategorySearchParam;
import pers.candyboyou.commodity.business.model.param.admin.RelateCategoryParam;
import pers.candyboyou.commodity.business.model.vo.admin.*;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    Long saveOrUpdateCategory(CategorySaveOrUpdateParam categorySaveParam);

    PageResult<CategoryBaseVO> getCategories(CategorySearchParam searchParam);

    PageResult<SimpleCommodityInfoVO> getSimpleCommodityInfos(Long categoryId, QueryParam queryParam);

    void relateCommodities(RelateCategoryParam relateCategoryParam);

    Map<Long, String> getCategoryNamesByIds(List<Long> categoryIds);

    /**
     * 根据分类ID获取对应的属性list
     */
    List<AttributeNameDTO> getAttributeNamesByCategoryId(Long categoryId);

    List<CategoryIdAndNameVO> getAllCategoryIdAndName();

    void saveOrUpdateCategoryWithCommodityRelation(Long id, Long categoryId, Long commodityId);

    AllListParamOfCategoryVO getAllList();

    List<OptionCommonVO> getSubCategoryListById(Long id);

    List<OptionCommonVO> getCategoryListByLevel(Integer level);

    CategoryDetailVO getCategoryById(Long id);

    List<CategoryWithCommodityVO> getCommodityListOfCategory(List<Long> categoryIds, Long commodityId);

}
