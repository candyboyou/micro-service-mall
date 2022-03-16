package pers.candyboyou.commodity.business.mapper.admin;

import org.springframework.stereotype.Repository;
import pers.candyboyou.commodity.business.model.dto.AttributeNameDTO;
import pers.candyboyou.commodity.business.model.dto.AttributeWithValueDTO;

import java.util.List;

@Repository
public interface TCommodityCategoryAttributeRelationMapper {

    List<AttributeNameDTO> getAttributeNamesByCategoryId(Long categoryId);

}
