package pers.candyboyou.commodity.business.mapper.admin;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.candyboyou.commodity.business.model.dto.PictureDTO;
import pers.candyboyou.commodity.business.model.param.admin.PictureSaveParam;

import java.util.List;

@Repository
public interface TCommodityPictureMapper {

    List<PictureDTO> selectMainPictureByCommodityIds(@Param("commodityIds") List<Long> commodityIds);

    List<PictureDTO> selectPicturesByCommodityId(Long id);

    void batchInsertPicturesByCommodityId(@Param("albumPics") List<PictureSaveParam> albumPics, Long id);

    /**
     * 根据商品id删除图片
     */
    void deleteByCommodityId(Long commodityId);

    /**
     * 根据商品的id获取对应的
     */
    List<Long> selectPictureIdsByCommodityId(Long commodityId);

    /**
     * 批量更新商品的图片
     */
    void batchUpdatePicture(@Param("updateParams") List<PictureSaveParam> pictureUpdateParams);

    /**
     * 批量删除picture
     */
    void batchDeleteByPictureIds(@Param("pictureIds") List<Long> delPictureIds);
}
