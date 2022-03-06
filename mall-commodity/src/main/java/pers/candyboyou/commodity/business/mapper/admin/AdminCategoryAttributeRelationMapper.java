package pers.candyboyou.commodity.business.mapper.admin;

import pers.candyboyou.commodity.business.model.dto.AttributeWithValueDTO;

import java.util.List;

public interface AdminCategoryAttributeRelationMapper {

    List<AttributeWithValueDTO> getAttributeIdsByCategoryId(Long categoryId);

}
