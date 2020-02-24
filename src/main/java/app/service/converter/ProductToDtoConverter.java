package app.service.converter;

import app.model.entities.Product;
import app.model.entities.dto.ProductDto;
import app.model.entities.dto.RemainsDto;

public class ProductToDtoConverter {

    private ProductToDtoConverter(){}

    public static ProductDto toProductDto(Product product){
        return ProductDto.builder()
                .name(product.getName())
                .vendorCode(product.getVendorCode())
                .purchasePrice(product.getPurchasePrice())
                .sellingPrice(product.getPurchasePrice())
                .build();
    }

    public static RemainsDto toRemainsDto(Product product){
        return RemainsDto.builder()
                .name(product.getName())
                .vendorCode(product.getVendorCode())
                .build();
    }
}
