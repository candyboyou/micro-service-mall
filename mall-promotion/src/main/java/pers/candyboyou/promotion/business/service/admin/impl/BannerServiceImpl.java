package pers.candyboyou.mallpromotion.business.service.admin.impl;

import io.candyboyou.common.framework.model.vo.ListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.mallpromotion.business.mapper.admin.THomeBannerMapper;
import pers.candyboyou.mallpromotion.business.model.admin.BannerSearchParam;
import pers.candyboyou.mallpromotion.business.model.admin.dto.BannerInfoOfListDTO;
import pers.candyboyou.mallpromotion.business.model.admin.dto.BannerSaveDTO;
import pers.candyboyou.mallpromotion.business.model.admin.param.BannerSaveParam;
import pers.candyboyou.mallpromotion.business.model.admin.vo.BannerInfoOfListVO;
import pers.candyboyou.mallpromotion.business.service.admin.BannerService;

import java.util.List;

@Service
@Slf4j
public class BannerServiceImpl implements BannerService {

    @Autowired
    private THomeBannerMapper tHomeBannerMapper;

    @Override
    public void saveOrUpdateBannerCommodity(BannerSaveParam bannerSaveParam) {
        BannerSaveDTO bannerSaveDTO = BannerSaveDTO.convertBannerSaveParam(bannerSaveParam);
        if (bannerSaveParam.getBannerId() == null) {
            tHomeBannerMapper.insertBanner(bannerSaveDTO);
        } else {
            tHomeBannerMapper.updateBanner(bannerSaveDTO);
        }
    }

    @Override
    public ListVO<BannerInfoOfListVO> getBannerList(BannerSearchParam bannerSearchParam) {
        List<BannerInfoOfListDTO> bannerInfoOfListDTOs = tHomeBannerMapper.selectByParam(bannerSearchParam);
        List<BannerInfoOfListVO> bannerInfoOfListVOS = BannerInfoOfListVO.convertBannerInfoOfListDTOs(bannerInfoOfListDTOs);
        int totalCount = tHomeBannerMapper.selectCountByParam(bannerSearchParam);
        ListVO<BannerInfoOfListVO> bannerInfoOfListVOListVO = new ListVO<>();
        bannerInfoOfListVOListVO.setList(bannerInfoOfListVOS);
        bannerInfoOfListVOListVO.setTotal(totalCount);
        bannerInfoOfListVOListVO.setPageNum(bannerSearchParam.getPageNum());
        bannerInfoOfListVOListVO.setPageSize(bannerInfoOfListVOS.size());
        return bannerInfoOfListVOListVO;
    }

}
