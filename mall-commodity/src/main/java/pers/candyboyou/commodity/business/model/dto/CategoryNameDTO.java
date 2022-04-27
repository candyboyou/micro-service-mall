package pers.candyboyou.commodity.business.model.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CategoryNameDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3273844358876943278L;

    private Long id;

    private String name;

}
