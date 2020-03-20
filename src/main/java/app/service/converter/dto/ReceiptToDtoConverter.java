package app.service.converter.dto;

import app.model.entities.docs.Receipt;
import app.model.entities.dto.ReceiptDTO;

public class ReceiptToDtoConverter {

    private ReceiptToDtoConverter(){}

    public static ReceiptDTO toReceiptDto(Receipt receipt){
        return ReceiptDTO.builder()
                .id(receipt.getId())
                .warehouse(receipt.getWarehouse())
                .product(receipt.getProduct())
                .build();
    }

}
