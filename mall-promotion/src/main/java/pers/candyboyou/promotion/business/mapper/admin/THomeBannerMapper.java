package pers.candyboyou.mallpromotion.business.mapper.admin;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.candyboyou.mallpromotion.business.model.admin.BannerSearchParam;
import pers.candyboyou.mallpromotion.business.model.admin.dto.BannerInfoOfListDTO;
import pers.candyboyou.mallpromotion.business.model.admin.dto.BannerSaveDTO;

import java.util.List;

@Repository
public interface THomeBannerMapper {

    void insertBanner(@Param("bannerSaveDTO") BannerSaveDTO bannerSaveDTO);

    void updateBanner(@Param("bannerSaveDTO") BannerSaveDTO bannerSaveDTO);

    List<BannerInfoOfListDTO> selectByParam(@Param("searchParam") BannerSearchParam searchParam);

    int selectCountByParam(@Param("searchParam") BannerSearchParam bannerSearchParam);
}
