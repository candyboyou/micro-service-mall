package pers.candyboyou.commodity.business.service.admin.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.commodity.business.mapper.admin.TCommodityPictureMapper;
import pers.candyboyou.commodity.business.model.dto.DescIdWithImgDTO;
import pers.candyboyou.commodity.business.service.admin.DescriptionService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DescriptionServiceImpl implements DescriptionService {

    @Autowired
    private TCommodityPictureMapper tCommodityPictureMapper;

    @Override
    public Map<Long, String> getDescIdWithImgByIds(List<Long> descriptionIds) {
        List<DescIdWithImgDTO> descIdWithImgDTOS = tCommodityPictureMapper.selectDescIdWithImgByIds(descriptionIds);
        return descIdWithImgDTOS.stream().collect(Collectors.toMap(DescIdWithImgDTO::getId, DescIdWithImgDTO::getImgUrl));
    }
}
