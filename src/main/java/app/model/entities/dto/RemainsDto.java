package app.model.entities.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RemainsDto {

    private String vendorCode;

    private String name;

}
