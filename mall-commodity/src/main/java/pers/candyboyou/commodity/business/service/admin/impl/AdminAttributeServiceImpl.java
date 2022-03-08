package pers.candyboyou.commodity.business.service.admin.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.commodity.business.mapper.admin.AdminAttributeMapper;
import pers.candyboyou.commodity.business.model.dto.AttributeIdWithIsSaleDTO;
import pers.candyboyou.commodity.business.model.param.admin.AttributeOfCommoditySaveParam;
import pers.candyboyou.commodity.business.service.admin.AdminAttributeService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdminAttributeServiceImpl implements AdminAttributeService {

    @Autowired
    private AdminAttributeMapper adminAttributeMapper;

    @Override
    public Map<Long, Integer> getAttributeIdToIsSaleMap(List<Long> attributeIds) {
        List<AttributeIdWithIsSaleDTO> attributeIdWithIsSaleDTOS = adminAttributeMapper.selectAttributeIdWithIsSale(attributeIds);
        return attributeIdWithIsSaleDTOS.stream().collect(Collectors.toMap(AttributeIdWithIsSaleDTO::getId, AttributeIdWithIsSaleDTO::getIsSaleAttr));
    }
}
