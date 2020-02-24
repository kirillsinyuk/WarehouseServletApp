package app.model.entities;

import app.model.entities.docs.Movement;
import app.model.entities.docs.Receipt;
import app.model.entities.docs.Sale;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String vendorCode;

    @Column
    private String name;

    @Column
    private BigDecimal purchasePrice;

    @Column
    private BigDecimal sellingPrice;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "movement_id")
    private Movement movement;

    @Column
    private boolean deleted;
}
