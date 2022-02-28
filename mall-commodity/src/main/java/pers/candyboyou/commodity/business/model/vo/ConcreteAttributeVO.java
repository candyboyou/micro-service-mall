package pers.candyboyou.commodity.business.model.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class ConcreteAttributeVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 2866353861588899546L;

    public static List<ConcreteAttributeVO> convertConcreteAttributeDTOS(List<ConcreteAttributeVO> concreteAttributeDTOList) {
        return null;
    }
}
