package app.model.entities.docs;

import app.model.entities.Product;
import app.model.entities.Warehouse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Warehouse warehouseFrom;

    @Column
    private Warehouse warehouseTo;

    @Column
    private List<Product> products;
}
