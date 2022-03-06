package pers.candyboyou.commodity.business.mapper.admin;

import pers.candyboyou.commodity.business.model.dto.SkuAttributeRelationDTO;

import java.util.List;

public interface AdminSkuAttributeRelationMapper {

    List<SkuAttributeRelationDTO> selectAttributesOfSkuBySkuIds(List<Long> skuIds);
}
