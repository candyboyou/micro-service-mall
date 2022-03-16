package pers.candyboyou.commodity.business.service.admin.impl;

import io.candyboyou.common.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.commodity.business.mapper.admin.TCommodityPictureMapper;
import pers.candyboyou.commodity.business.model.dto.PictureDTO;
import pers.candyboyou.commodity.business.model.param.admin.PictureSaveParam;
import pers.candyboyou.commodity.business.model.param.admin.SkuSaveParam;
import pers.candyboyou.commodity.business.model.vo.admin.PictureVO;
import pers.candyboyou.commodity.business.service.admin.PictureService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PictureServiceImpl implements PictureService {

    @Autowired
    private TCommodityPictureMapper tCommodityPictureMapper;

    @Override
    public Map<Long, String> getMainPictureUrlByCommodityIds(List<Long> commodityIds) {
        List<PictureDTO> pictureDTOs = tCommodityPictureMapper.selectMainPictureByCommodityIds(commodityIds);
        return pictureDTOs.stream().collect(Collectors.toMap(PictureDTO::getCommodityId, PictureDTO::getPictureUrl));
    }

    @Override
    public List<PictureVO> getPictureVOsByCommodityId(Long id) {
        List<PictureDTO> pictureDTOs  = tCommodityPictureMapper.selectPicturesByCommodityId(id);
        return PictureVO.convertPictureDTOs(pictureDTOs);
    }

    @Override
    public void savePicturesByCommodityId(List<PictureSaveParam> albumPics, Long id) {
        if (CollectionUtils.isEmpty(albumPics)) {
            return;
        }
        tCommodityPictureMapper.batchInsertPicturesByCommodityId(albumPics, id);
    }

    @Override
    public void deleteByCommodityId(Long commodityId) {
        tCommodityPictureMapper.deleteByCommodityId(commodityId);
    }

    @Override
    public void updatePictureService(List<PictureSaveParam> pictureSaveParams, Long commodityId) {
        if (CollectionUtils.isEmpty(pictureSaveParams)) {
            return;
        }
        List<Long> newPictureIds = pictureSaveParams.stream().map(PictureSaveParam::getId).toList();
        List<Long> delPictureIds = getPictureIdsByCommodityId(commodityId);
        delPictureIds.removeAll(newPictureIds);

        // 删除图片
        if (CollectionUtils.isNotEmpty(delPictureIds)) {
            tCommodityPictureMapper.batchDeleteByPictureIds(delPictureIds);
        }
        // 更新图片
        tCommodityPictureMapper.batchUpdatePicture(pictureSaveParams);

    }

    private List<Long> getPictureIdsByCommodityId(Long commodityId) {
        return tCommodityPictureMapper.selectPictureIdsByCommodityId(commodityId);
    }
}
