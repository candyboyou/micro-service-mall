package pers.candyboyou.commodity.business.mapper.admin;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.candyboyou.commodity.business.model.dto.AttributeNameDTO;
import pers.candyboyou.commodity.business.model.dto.AttributeWithValueDTO;

import java.util.List;

@Repository
public interface TCommodityCategoryAttributeRelationMapper {

    List<AttributeNameDTO> getAttributeNamesByCategoryId(Long categoryId);

    List<Long> selectAttributesByCategoryId(Long id);

    void batchInsertCategoryAttributeRelation(@Param("categoryId") Long categoryId, @Param("attributeIds") List<Long> attributeIds);

    void batchDeleteCategoryAttributeRelation(@Param("categoryId") Long categoryId, @Param("attributeIds") List<Long> insertAttributeIds);

}
