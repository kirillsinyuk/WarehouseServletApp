package app.service.converter.json;

import app.model.entities.docs.Movement;
import app.model.entities.dto.MovementDTO;
import app.service.converter.dto.MovementToDtoConverter;
import app.util.ValidateUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import sun.security.validator.ValidatorException;

import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

public class JsonMovementConverter {

    private final Gson gson;

    public JsonMovementConverter() {

        gson = new GsonBuilder().create();
    }

    public String convertMovementCollectionToJson(List<Movement> movements) {
        List<MovementDTO> movementDTOs = movements.stream().map(MovementToDtoConverter::toMovementDto).collect(Collectors.toList());
        JsonArray jarray = gson.toJsonTree(movementDTOs).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("movements", jarray);

        return jsonObject.toString();
    }

    public String convertMovementToJson(Movement movement) {
        JsonObject jsonObject = gson.toJsonTree(movement).getAsJsonObject();
        return jsonObject.toString();
    }

    public Movement parseMovementFromJson(String json) {
        try {
            Movement movementEntity = gson.fromJson(json, Movement.class);
            if (!ValidateUtil.isMovementValid(movementEntity)){
                throw new ValidatorException("entity is not valid");
            }
            movementEntity.setId(null);
            return movementEntity;
        } catch (JsonSyntaxException | ValidatorException e) {
            return null;
        }
    }

    public Long parseId(String json){
        Properties data = gson.fromJson(json, Properties.class);
        if (Objects.isNull(data)){
            return null;
        }
        return ValidateUtil.getLongParam(data.getProperty("id"));
    }

}
