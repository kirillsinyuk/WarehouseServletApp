package app.service.converter.json;

import app.model.entities.Warehouse;
import app.util.ValidateUtil;
import com.google.gson.*;
import sun.security.validator.ValidatorException;

import java.util.Objects;
import java.util.Properties;

public class JsonWarehouseConverter {

    private final Gson gson;

    public JsonWarehouseConverter() {

        gson = new GsonBuilder().create();
    }

    public Warehouse parseWarehouseFromJson(String json, boolean allFieldsRequired) {
        try {
            Warehouse warehouseEntity = gson.fromJson(json, Warehouse.class);
            if (!ValidateUtil.isWarehouseValid(warehouseEntity, allFieldsRequired) ){
                throw new ValidatorException("entity is not valid");
            }
            warehouseEntity.setId(null);
            return warehouseEntity;
        } catch (JsonSyntaxException | ValidatorException e) {
            return null;
        }
    }

    public Warehouse updateWarehouseFromJson(String json, boolean allFieldsRequired) {
        try {
            Warehouse warehouseEntity = gson.fromJson(json, Warehouse.class);
            if (!ValidateUtil.isWarehouseValid(warehouseEntity, allFieldsRequired) || !ValidateUtil.isWarehiuseIdValid(warehouseEntity.getId())){
                throw new ValidatorException("entity is not valid");
            }
            return warehouseEntity;
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

    public String parseName(String json){
        Properties data = gson.fromJson(json, Properties.class);
        if (Objects.isNull(data)){
            return null;
        }
        return data.getProperty("name");
    }

    public String convertWarehouseToJson(Warehouse warehouse) {
        JsonObject jsonObject = gson.toJsonTree(warehouse).getAsJsonObject();
        return jsonObject.toString();
    }
}
