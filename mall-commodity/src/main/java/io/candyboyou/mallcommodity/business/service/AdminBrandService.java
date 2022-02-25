package io.candyboyou.mallcommodity.business.service;

import io.candyboyou.mallcommodity.business.model.param.BrandSearchParam;
import io.candyboyou.mallcommodity.business.model.vo.BrandVO;

import java.util.List;

public interface AdminBrandService {

    List<BrandVO> getBrands(BrandSearchParam searchParam);

}
