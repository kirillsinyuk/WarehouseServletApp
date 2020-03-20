package app.service.converter.json;

import app.model.entities.docs.Receipt;
import app.model.entities.dto.ReceiptDTO;
import app.service.converter.dto.ReceiptToDtoConverter;
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

public class JsonReceiptConverter {

    private final Gson gson;

    public JsonReceiptConverter() {

        gson = new GsonBuilder().create();
    }

    public String convertReceiptCollectionToJson(List<Receipt> movements) {
        List<ReceiptDTO> receiptDTOs = movements.stream().map(ReceiptToDtoConverter::toReceiptDto).collect(Collectors.toList());
        JsonArray jarray = gson.toJsonTree(receiptDTOs).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("receipts", jarray);

        return jsonObject.toString();
    }

    public String convertReceiptToJson(Receipt receipt) {
        JsonObject jsonObject = gson.toJsonTree(receipt).getAsJsonObject();
        return jsonObject.toString();
    }

    public Receipt parseReceiptFromJson(String json) {
        try {
            Receipt receipt = gson.fromJson(json, Receipt.class);
            if (!ValidateUtil.isReceiptValid(receipt)){
                throw new ValidatorException("entity is not valid");
            }
            receipt.setId(null);
            return receipt;
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
