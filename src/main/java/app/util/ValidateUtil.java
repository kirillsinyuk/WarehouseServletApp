package app.util;

import app.model.entities.Product;
import app.model.entities.Warehouse;
import app.model.entities.docs.Movement;
import app.model.entities.docs.Receipt;
import app.model.entities.docs.Sale;
import app.service.DaoFactory;
import org.codehaus.plexus.util.StringUtils;

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
            return StringUtils.isNotBlank(strParam);
        } else if (Objects.nonNull(strParam)) {
            return !strParam.trim().isEmpty();
        }
        return true;
    }

    public static boolean isWarehiuseIdValid (Long id) {
        return Objects.nonNull(id) && DaoFactory.getInstance(DaoFactory.DaoType.WAREHOUSE).getById(Warehouse.class, id) != null;
    }

    public static boolean isProductIdValid (Long id) {
        return Objects.nonNull(id) && DaoFactory.getInstance(DaoFactory.DaoType.PRODUCT).getById(Product.class, id) != null;
    }

    public static boolean isProductValid(Product product, boolean required){
        return Objects.nonNull(product)
                && stringValid(product.getName(), required)
                && stringValid(product.getVendorCode(), required)
                && priceIsValid(product.getPurchasePrice(), required)
                && priceIsValid(product.getSellingPrice(), required)
                && isWarehouseValid(product.getWarehouse(), required);
    }

    public static boolean isMovementIdValid (Long id) {
        return Objects.nonNull(id) && DaoFactory.getInstance(DaoFactory.DaoType.MOVEMENT).getById(Movement.class, id) != null;
    }


    public static boolean isMovementValid(Movement move){
        return Objects.nonNull(move)
                && isWarehouseValid(move.getWarehouseFrom(), true)
                && isWarehouseValid(move.getWarehouseTo(), true)
                && move.getProducts().stream().allMatch(pr -> isProductValid(pr, true) && isProductIdValid(pr.getId()));
    }

    public static boolean isReceiptValid(Receipt receipt){
        return Objects.nonNull(receipt)
                && isWarehouseValid(receipt.getWarehouse(), true)
                && receipt.getProduct().stream().allMatch(pr -> isProductValid(pr, true) && isProductIdValid(pr.getId()));
    }

    public static boolean isSaleValid(Sale sale){
        return Objects.nonNull(sale)
                && isWarehouseValid(sale.getWarehouse(), true)
                && sale.getProducts().stream().allMatch(pr -> isProductValid(pr, true) && isProductIdValid(pr.getId()));
    }

    public static boolean isWarehouseValid(Warehouse warehouse, boolean required){
        return Objects.nonNull(warehouse)
                && stringValid(warehouse.getName(), required);
    }

    public static Long getLongParam(String param) {
        try {
            return Long.parseLong(param);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
