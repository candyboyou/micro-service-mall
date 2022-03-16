package pers.candyboyou.commodity.business.service.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.candyboyou.common.framework.model.vo.ListVO;
import pers.candyboyou.commodity.business.model.dto.AttributeNameDTO;
import pers.candyboyou.commodity.business.model.param.admin.CategorySaveOrUpdateParam;
import pers.candyboyou.commodity.business.model.param.admin.CategorySearchParam;
import pers.candyboyou.commodity.business.model.param.admin.RelateCategoryParam;
import pers.candyboyou.commodity.business.model.vo.admin.AttributeValueOfSkuVO;
import pers.candyboyou.commodity.business.model.vo.admin.AttributeValueVO;
import pers.candyboyou.commodity.business.model.vo.admin.CategoryVO;
import pers.candyboyou.commodity.business.model.vo.admin.SimpleCommodityInfoVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CategoryService {

    void saveOrUpdateCategory(CategorySaveOrUpdateParam categorySaveParam);

    ListVO<CategoryVO> getCategories(CategorySearchParam searchParam);

    ListVO<SimpleCommodityInfoVO> getSimpleCommodityInfos(Long categoryId, QueryParam queryParam);

    void relateCommodities(RelateCategoryParam relateCategoryParam);

    Map<Long, String> getCategoryNamesByIds(List<Long> categoryIds);

    /**
     * 根据分类ID获取对应的属性list
     */
    List<AttributeNameDTO> getAttributeNamesByCategoryId(Long categoryId);

}
