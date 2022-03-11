package pers.candyboyou.commodity.business.mapper.admin;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.candyboyou.commodity.business.model.entity.SpuAttributeEntity;

import java.util.List;

@Repository
public interface TCommoditySpuMapper {

    List<SpuAttributeEntity> selectSpuAttributeEntities(@Param("ids") List<Long> ids);

}
