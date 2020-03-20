package app.model.entities.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDto {

    private String vendorCode;

    private String name;

    private BigDecimal purchasePrice;

    private BigDecimal sellingPrice;
}
