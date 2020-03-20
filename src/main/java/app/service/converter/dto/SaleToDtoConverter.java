package app.service.converter.dto;

import app.model.entities.docs.Sale;
import app.model.entities.dto.SaleDTO;

public class SaleToDtoConverter {

    private SaleToDtoConverter(){}

    public static SaleDTO toSaleDto(Sale sale){
        return SaleDTO.builder()
                .id(sale.getId())
                .warehouse(sale.getWarehouse())
                .product(sale.getProducts())
                .build();
    }

}
