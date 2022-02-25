package io.candyboyou.mallcommodity.business.service.impl;

import io.candyboyou.mallcommodity.business.model.param.BrandSearchParam;
import io.candyboyou.mallcommodity.business.model.vo.BrandVO;
import io.candyboyou.mallcommodity.business.service.AdminBrandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminBrandServiceImpl implements AdminBrandService {


    @Override
    public List<BrandVO> getBrands(BrandSearchParam searchParam) {
        return null;
    }
}
