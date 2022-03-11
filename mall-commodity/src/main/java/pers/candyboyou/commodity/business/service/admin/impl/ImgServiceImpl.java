package pers.candyboyou.commodity.business.service.admin.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.commodity.business.mapper.admin.TCommodityPictureMapper;
import pers.candyboyou.commodity.business.model.dto.DescIdWithImgDTO;
import pers.candyboyou.commodity.business.model.vo.admin.ImgVO;
import pers.candyboyou.commodity.business.service.admin.ImgService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ImgServiceImpl implements ImgService {

    @Autowired
    private TCommodityPictureMapper tCommodityPictureMapper;

    @Override
    public Map<Long, ImgVO> getDescIdWithImgByIds(List<Long> descriptionIds) {
        List<DescIdWithImgDTO> descIdWithImgDTOS = tCommodityPictureMapper.selectDescIdWithImgByIds(descriptionIds);
        Map<Long, ImgVO> idToImgVOMap = new HashMap<>();
        for (DescIdWithImgDTO descIdWithImgDTO : descIdWithImgDTOS) {
            ImgVO imgVO = ImgVO.convertImgDTO(descIdWithImgDTO);
            idToImgVOMap.put(imgVO.getId(), imgVO);
        }
        return idToImgVOMap;
    }

    @Override
    public String getMainImgUrlByCommodityIds(List<Long> commodityIds) {
        List<String> mainImgUrls = tCommodityPictureMapper.selectMainImgByCommodityIds(commodityIds);
        return null;
    }
}
