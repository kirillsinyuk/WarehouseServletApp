package app.model.entities.dto;

import app.model.entities.Product;
import app.model.entities.Warehouse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReceiptDTO {

    private Long id;

    private Warehouse warehouse;

    private List<Product> product;
}
