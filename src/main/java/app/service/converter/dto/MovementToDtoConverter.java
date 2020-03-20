package app.service.converter.dto;

import app.model.entities.docs.Movement;
import app.model.entities.dto.MovementDTO;

public class MovementToDtoConverter {

    private MovementToDtoConverter(){}

    public static MovementDTO toMovementDto(Movement movement){
        return MovementDTO.builder()
                .id(movement.getId())
                .warehouseFrom(movement.getWarehouseFrom())
                .warehouseTo(movement.getWarehouseTo())
                .products(movement.getProducts())
                .build();
    }

}
