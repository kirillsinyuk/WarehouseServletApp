package app.model.entities.dto;

import app.model.entities.Product;
import app.model.entities.Warehouse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MovementDTO {

    private Long id;

    private Warehouse warehouseFrom;

    private Warehouse warehouseTo;

    private List<Product> products;
}
