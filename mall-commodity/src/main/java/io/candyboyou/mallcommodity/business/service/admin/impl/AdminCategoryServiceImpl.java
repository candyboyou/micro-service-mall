package io.candyboyou.mallcommodity.business.service.admin.impl;

import io.candyboyou.mallcommodity.business.mapper.admin.AdminCategoryMapper;
import io.candyboyou.mallcommodity.business.model.dto.CategorySaveOrUpdateDTO;
import io.candyboyou.mallcommodity.business.model.param.admin.CategorySaveOrUpdateParam;
import io.candyboyou.mallcommodity.business.service.admin.AdminCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        adminCategoryMapper.saveOrUpdateCategory(categorySaveOrUpdateDTO);
    }
}
