package app.service.converter;

import app.model.entities.Product;
import app.model.entities.Warehouse;
import app.model.entities.dto.ProductDto;
import app.model.entities.dto.RemainsDto;
import app.util.ValidateUtil;
import com.google.gson.*;
import sun.security.validator.ValidatorException;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class JsonConverter {

    private final Gson gson;

    public JsonConverter() {

        gson = new GsonBuilder().create();
    }

    public String convertProductCollectionToJson(List<Product> products) {
        List<ProductDto> productDtos = products.stream().map(ProductToDtoConverter::toProductDto).collect(Collectors.toList());
        JsonArray jarray = gson.toJsonTree(productDtos).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("products", jarray);

        return jsonObject.toString();
    }

    public String convertRemainsMapToDto(Map<RemainsDto, Long> remainsDtos) {
        JsonArray jarray = gson.toJsonTree(remainsDtos).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("products", jarray);

        return jsonObject.toString();
    }

    public String convertProductToJson(Product product) {
        JsonObject jsonObject = gson.toJsonTree(product).getAsJsonObject();
        return jsonObject.toString();
    }

    public Product parseProductFromJson(String json, boolean allFieldsRequired) {
        try {
            Product productEntity = gson.fromJson(json, Product.class);
            if (!ValidateUtil.isProductValid(productEntity, allFieldsRequired) || !ValidateUtil.isWarehiuseIdValid(productEntity.getWarehouse().getId())){
                throw new ValidatorException("entity is not valid");
            }
            return productEntity;
        } catch (JsonSyntaxException | ValidatorException e) {
           return null;
        }
    }

    public Warehouse parseWarehouseFromJson(String json, boolean allFieldsRequired) {
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
        return ValidateUtil.getLongParam(data.getProperty("id"));
    }

    public String parseName(String json){
        Properties data = gson.fromJson(json, Properties.class);
        return data.getProperty("name");
    }

    public String convertWarehouseToJson(Warehouse warehouse) {
        JsonObject jsonObject = gson.toJsonTree(warehouse).getAsJsonObject();
        return jsonObject.toString();
    }
}
