package app.util;

import app.entities.Product;
import app.entities.Warehouse;
import app.service.FactoryDao;
import sun.security.validator.ValidatorException;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class ValidateUtil {

    private ValidateUtil(){}

    private static String getStringParam(HttpServletRequest request, String fieldName, boolean required) throws ValidatorException {
        String fieldValue = request.getParameter(fieldName);
        if ((fieldValue == null || fieldValue.trim().isEmpty()) && required) {
            throw new ValidatorException("Field is required");
        }

        return fieldValue;
    }

    private static BigDecimal getBigDecimalParam(HttpServletRequest request, String fieldName, boolean required) throws ValidatorException, NumberFormatException {
        BigDecimal fieldValue;
            if (required && !request.getParameterMap().containsKey(fieldName)) {
                throw new ValidatorException("Field is required");
            } else {
                fieldValue = new BigDecimal(request.getParameter(fieldName));
            }
        return fieldValue;
    }

    private static Long getLongParam(HttpServletRequest request, String fieldName, boolean required) throws ValidatorException, NumberFormatException {
        Long fieldValue;
        if (required && !request.getParameterMap().containsKey(fieldName)) {
            throw new ValidatorException("Field is required");
        } else {
            fieldValue = Long.parseLong(request.getParameter(fieldName));
        }
        return fieldValue;
    }

    public static Product validateAndCreateProduct(HttpServletRequest req){
        Product product = null;
        try {
            String name = ValidateUtil.getStringParam(req, "name", false);
            String vendorCode = ValidateUtil.getStringParam(req, "vendorCode", false);
            BigDecimal purchasePrice = ValidateUtil.getBigDecimalParam(req, "purchasePrice", false);
            BigDecimal sellingPrice = ValidateUtil.getBigDecimalParam(req, "sellingPrice", false);
            Long warehouse_id = ValidateUtil.getLongParam(req, "warehouse", true);
            Warehouse warehouse = FactoryDao.getInstance().getWarehouseDAO().getWarehousetById(warehouse_id);
            if (warehouse == null){
                throw  new ValidatorException("Warehouse not found.");
            }
            product = Product.builder()
                    .name(name)
                    .vendorCode(vendorCode)
                    .purchasePrice(purchasePrice)
                    .sellingPrice(sellingPrice)
                    .warehouse(warehouse)
                    .build();
        } catch (ValidatorException | NumberFormatException e){
            e.printStackTrace();


        }
        return product;
    }

    public static Warehouse validateAndCreateWarehouse(HttpServletRequest req) {
        Warehouse warehouse = null;
        try {
            String name = ValidateUtil.getStringParam(req, "name", true);
            warehouse = Warehouse.builder()
                    .name(name)
                    .build();
        } catch (ValidatorException e){
            e.printStackTrace();
        }
        return warehouse;
    }

    public static Long getIdFromReq(HttpServletRequest req){
        Long id = null;
        try {
            id = ValidateUtil.getLongParam(req, "id", true);
        } catch (ValidatorException e){
            e.printStackTrace();
        }
        return id;
    }
}
