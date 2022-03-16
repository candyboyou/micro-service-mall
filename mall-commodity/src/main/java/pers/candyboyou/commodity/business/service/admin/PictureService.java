package pers.candyboyou.commodity.business.service.admin;

import pers.candyboyou.commodity.business.model.param.admin.PictureSaveParam;
import pers.candyboyou.commodity.business.model.vo.admin.PictureVO;

import java.util.List;
import java.util.Map;

public interface PictureService {

    Map<Long, String> getMainPictureUrlByCommodityIds(List<Long> commodityIds);

    List<PictureVO> getPictureVOsByCommodityId(Long id);

    void savePicturesByCommodityId(List<PictureSaveParam> albumPics, Long id);

    /**
     * 删除商品id关联的图片
     */
    void deleteByCommodityId(Long commodityId);

    /**
     * 更新对应的商品的图片信息
     */
    void updatePictureService(List<PictureSaveParam> pictureSaveParams, Long commodityId);
}
