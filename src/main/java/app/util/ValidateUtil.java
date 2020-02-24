package app.util;

import app.model.entities.Product;
import app.model.entities.Warehouse;
import app.service.FactoryDao;

import java.math.BigDecimal;
import java.util.Objects;

public class ValidateUtil {

    private ValidateUtil(){}

    private static boolean priceIsValid(BigDecimal price, boolean required) {
        if (required) {
            return Objects.nonNull(price) && price.compareTo(BigDecimal.ZERO) != -1;
        } else if (Objects.nonNull(price)) {
            return price.compareTo(BigDecimal.ZERO) != -1;
        }
        return true;
    }

    private static boolean stringValid (String strParam, boolean required) {
        if (required) {
            return Objects.nonNull(strParam) && !strParam.trim().isEmpty();
        } else if (Objects.nonNull(strParam)) {
            return !strParam.trim().isEmpty();
        }
        return true;
    }

    public static boolean isWarehiuseIdValid (Long id) {
        return Objects.nonNull(id) && FactoryDao.getInstance().getWarehouseDAO().getWarehouseById(id) != null;
    }

    public static boolean isProductIdValid (Long id) {
        return Objects.nonNull(id) && FactoryDao.getInstance().getProductDAO().getProductById(id) != null;
    }

    public static boolean isProductValid(Product product, boolean required){
        return stringValid(product.getName(), required)
                && stringValid(product.getVendorCode(), required)
                && priceIsValid(product.getPurchasePrice(), required)
                && priceIsValid(product.getSellingPrice(), required)
                && isWarehouseValid(product.getWarehouse(), required);
    }

    public static boolean isWarehouseValid(Warehouse warehouse, boolean required){
        return stringValid(warehouse.getName(), required);
    }

    public static Long getLongParam(String param) {
        try {
            return Long.parseLong(param);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
