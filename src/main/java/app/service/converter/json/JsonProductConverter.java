package app.service.converter.json;

import app.model.entities.Product;
import app.model.entities.dto.ProductDto;
import app.model.entities.dto.RemainsDto;
import app.service.converter.dto.ProductToDtoConverter;
import app.util.ValidateUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import sun.security.validator.ValidatorException;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

public class JsonProductConverter {

    private final Gson gson;

    public JsonProductConverter() {

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
        JsonObject jsonObject = gson.toJsonTree(remainsDtos).getAsJsonObject();
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
            productEntity.setId(null);
            return productEntity;
        } catch (JsonSyntaxException | ValidatorException e) {
            return null;
        }
    }

    public Product updateProductFromJson(String json, boolean allFieldsRequired) {
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
}
