package app.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Builder
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
    @JoinColumn(name="warehouse_id", nullable=false)
    private Warehouse warehouse;
}
