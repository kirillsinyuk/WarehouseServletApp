package app.service.converter;

import app.entities.Product;
import app.entities.Warehouse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.List;

public class JsonConverter {

    private final Gson gson;

    public JsonConverter() {

        gson = new GsonBuilder().create();
    }

    public String convertProductCollectionToJson(List<Product> products) {

        JsonArray jarray = gson.toJsonTree(products).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("products", jarray);

        return jsonObject.toString();
    }

    public String convertProductToJson(Product product) {
        JsonObject jsonObject = gson.toJsonTree(product).getAsJsonObject();
        return jsonObject.toString();
    }

    public String convertWarehouseCollectionToJson(List<Warehouse> warehouses) {

        JsonArray jarray = gson.toJsonTree(warehouses).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("warehouses", jarray);

        return jsonObject.toString();
    }

    public String convertWarehouseToJson(Warehouse warehouse) {
        JsonObject jsonObject = gson.toJsonTree(warehouse).getAsJsonObject();
        return jsonObject.toString();
    }
}
