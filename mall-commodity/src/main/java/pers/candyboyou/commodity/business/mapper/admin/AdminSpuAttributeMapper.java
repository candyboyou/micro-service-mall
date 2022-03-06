package pers.candyboyou.commodity.business.mapper.admin;

import org.apache.ibatis.annotations.Param;
import pers.candyboyou.commodity.business.model.entity.SpuAttributeEntity;

import java.util.List;

public interface AdminSpuAttributeMapper {

    List<SpuAttributeEntity> selectSpuAttributeEntities(@Param("ids") List<Long> ids);

}
