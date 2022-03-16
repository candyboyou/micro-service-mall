package pers.candyboyou.commodity.business.model.vo.admin;

import io.candyboyou.common.utils.CollectionUtils;
import lombok.Data;
import pers.candyboyou.commodity.business.model.dto.SimpleCommodityDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class SimpleCommodityInfoVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -1161923708409537701L;

    private Long id;

    private String name;

    public static List<SimpleCommodityInfoVO> convertSimpleCommodities(List<SimpleCommodityDTO> simpleCommodityDTOs) {
        if (CollectionUtils.isEmpty(simpleCommodityDTOs)) {
            return new ArrayList<>();
        }
        List<SimpleCommodityInfoVO> simpleCommodityInfoVOs = new ArrayList<>(simpleCommodityDTOs.size());
        for (SimpleCommodityDTO simpleCommodityDTO : simpleCommodityDTOs) {
            SimpleCommodityInfoVO simpleCommodityInfoVO = new SimpleCommodityInfoVO();
            simpleCommodityInfoVO.setId(simpleCommodityDTO.getId());
            simpleCommodityInfoVO.setName(simpleCommodityDTO.getName());
            simpleCommodityInfoVOs.add(simpleCommodityInfoVO);
        }
        return simpleCommodityInfoVOs;
    }
}
