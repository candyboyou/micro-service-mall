package pers.candyboyou.commodity.business.mapper.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.candyboyou.commodity.business.model.dto.CommodityOfListDTO;
import pers.candyboyou.commodity.business.model.dto.CommoditySaveDTO;
import pers.candyboyou.commodity.business.model.dto.SimpleCommodityDTO;
import pers.candyboyou.commodity.business.model.entity.CommodityEntity;
import pers.candyboyou.commodity.business.model.param.admin.CommoditySearchParam;
import pers.candyboyou.commodity.business.model.param.admin.CommodityStatusParam;

import java.util.List;

@Repository
public interface TCommodityMapper {

    List<SimpleCommodityDTO> selectSimpleCommodityByCategoryId(Long categoryId, QueryParam queryParam);

    int selectSimpleCommodityCountsByCategoryId(Long categoryId, QueryParam queryParam);

    void deleteCategory(Long id);

    void updateCategoryOfCommodity(Long categoryId, Long commodityId);

    List<CommodityOfListDTO> selectCommodityOfList(@Param("param") CommoditySearchParam commoditySearchParam);

    int selectCommodityCounts(@Param("param") CommoditySearchParam commoditySearchParam);

    CommodityEntity selectCommodityById(Long id);

    void updateStatusOfCommodity(@Param("commodityStatusParam") CommodityStatusParam commodityStatusParam);

    Long saveCommodity(@Param("saveParam") CommoditySaveDTO commoditySaveDTO);

    /**
     * 更新商品的详情
     */
    void updateCommodity(@Param("updateParam") CommoditySaveDTO commodityUpdateDTO);

    /**
     * 根据商品的id删除商品
     */
    void deleteCommodity(Long id);

    void deleteCommodityByName(String product_name);
}
