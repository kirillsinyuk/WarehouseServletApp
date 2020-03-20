package app.service.converter.json;

import app.model.entities.docs.Receipt;
import app.model.entities.docs.Sale;
import app.model.entities.dto.SaleDTO;
import app.service.converter.dto.SaleToDtoConverter;
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

public class JsonSaleConverter {

    private final Gson gson;

    public JsonSaleConverter() {

        gson = new GsonBuilder().create();
    }

    public String convertReceiptCollectionToJson(List<Sale> sales) {
        List<SaleDTO> saleDTOS = sales.stream().map(SaleToDtoConverter::toSaleDto).collect(Collectors.toList());
        JsonArray jarray = gson.toJsonTree(saleDTOS).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("sales", jarray);

        return jsonObject.toString();
    }

    public String convertSaleToJson(Sale sale) {
        JsonObject jsonObject = gson.toJsonTree(sale).getAsJsonObject();
        return jsonObject.toString();
    }

    public Sale parseSaleFromJson(String json) {
        try {
            Sale sale = gson.fromJson(json, Sale.class);
            if (!ValidateUtil.isSaleValid(sale)){
                throw new ValidatorException("entity is not valid");
            }
            sale.setId(null);
            return sale;
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
