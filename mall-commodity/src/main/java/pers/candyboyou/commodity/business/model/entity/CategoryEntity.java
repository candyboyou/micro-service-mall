package pers.candyboyou.commodity.business.model.entity;

import com.google.gson.Gson;
import io.candyboyou.common.framework.model.base.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.candyboyou.commodity.business.model.param.admin.CategorySaveOrUpdateParam;

import java.io.Serial;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryEntity extends Entity {

    @Serial
    private static final long serialVersionUID = 7594666187351315237L;

    /**
     * 上级分类ID
     */
    private Long parentId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 商品数量
     */
    private Integer commodityCount;

    /**
     * 分类的位置
     */
    private Integer place;

    /**
     * 显示状态
     */
    private Integer isShow;

    /**
     * 在同一级别中的排序值
     */
    private Integer sort;

    /**
     * 分类图标
     */
    private String iconUrl;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 分类的描述
     */
    private String description;

    public static CategoryEntity convert(CategorySaveOrUpdateParam categorySaveParam) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categorySaveParam.getName());
        categoryEntity.setParentId(categorySaveParam.getParentId());
        categoryEntity.setLevel(categorySaveParam.getLevel());
        categoryEntity.setPlace(categorySaveParam.getPlace());
        categoryEntity.setIsShow(categorySaveParam.getIsShow());
        categoryEntity.setSort(categorySaveParam.getSort());
        categoryEntity.setIconUrl(categorySaveParam.getIconUrl());
        List<String> keyWords = categorySaveParam.getKeyWords();
        String keyWordsJson = new Gson().toJson(keyWords);
        categoryEntity.setKeywords(keyWordsJson);
        categoryEntity.setDescription(categorySaveParam.getDescription());
        categoryEntity.setCreateTime(new Date());
        categoryEntity.setUpdateTime(new Date());
        return categoryEntity;
    }
}
