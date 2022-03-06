package pers.candyboyou.commodity.business.service.admin.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.commodity.business.mapper.admin.AdminDescriptionMapper;
import pers.candyboyou.commodity.business.model.dto.DescIdWithImg;
import pers.candyboyou.commodity.business.model.dto.DescIdWithImgDTO;
import pers.candyboyou.commodity.business.service.admin.AdminDescriptionService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdminDescriptionServiceImpl implements AdminDescriptionService {

    @Autowired
    private AdminDescriptionMapper adminDescriptionMapper;

    @Override
    public Map<Long, String> getDescIdWithImgByIds(List<Long> descriptionIds) {
        List<DescIdWithImgDTO> descIdWithImgDTOS = adminDescriptionMapper.selectDescIdWithImgByIds(descriptionIds);
        return descIdWithImgDTOS.stream().collect(Collectors.toMap(DescIdWithImgDTO::getId, DescIdWithImgDTO::getImgUrl));
    }
}
