package pers.candyboyou.commodity.business.service.admin.impl;

import io.candyboyou.common.framework.model.vo.ListVO;
import pers.candyboyou.commodity.business.mapper.admin.AdminCategoryMapper;
import pers.candyboyou.commodity.business.model.dto.CategoryDTO;
import pers.candyboyou.commodity.business.model.dto.CategorySaveOrUpdateDTO;
import pers.candyboyou.commodity.business.model.param.admin.CategorySaveOrUpdateParam;
import pers.candyboyou.commodity.business.model.param.admin.CategorySearchParam;
import pers.candyboyou.commodity.business.model.param.admin.RelateCategoryParam;
import pers.candyboyou.commodity.business.model.vo.admin.CategoryVO;
import pers.candyboyou.commodity.business.model.vo.admin.SimpleCommodityInfoVO;
import pers.candyboyou.commodity.business.service.admin.AdminCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {

    @Autowired
    private AdminCategoryMapper adminCategoryMapper;

    @Override
    public void saveOrUpdateCategory(CategorySaveOrUpdateParam categorySaveOrUpdateParam) {
        if (categorySaveOrUpdateParam == null) {
            return;
        }
        CategorySaveOrUpdateDTO categorySaveOrUpdateDTO = CategorySaveOrUpdateDTO.convert(categorySaveOrUpdateParam);
        Long categoryId = categorySaveOrUpdateParam.getCategoryId();
        if (categoryId == null) {
            adminCategoryMapper.insertCategory(categorySaveOrUpdateDTO);
            return;
        }

        if (categorySaveOrUpdateParam.getIsDelete() == 1) {
            adminCategoryMapper.deleteCategory(categoryId);
            return;
        }

        adminCategoryMapper.updateCategory(categorySaveOrUpdateDTO);
    }

    @Override
    public ListVO<CategoryVO> getCategories(CategorySearchParam searchParam) {
        List<CategoryDTO> categoryDTOList = adminCategoryMapper.selectCategoriesBySearchParam(searchParam);

        return null;
    }

    @Override
    public ListVO<SimpleCommodityInfoVO> getSimpleCommodityInfos(Long categoryId) {
        return null;
    }

    @Override
    public void relateCommodities(RelateCategoryParam relateCategoryParam) {

    }


}
