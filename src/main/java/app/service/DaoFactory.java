package app.service;

import app.dao.impl.MovementDAOImpl;
import app.dao.impl.ProductDAOImpl;
import app.dao.impl.ReceiptDAOImpl;
import app.dao.impl.SaleDAOImpl;
import app.dao.impl.WarehouseDAOImpl;

public class DaoFactory {

    private static ProductDAOImpl productDAO;
    private static WarehouseDAOImpl warehouseDAO;
    private static MovementDAOImpl movementDAO;
    private static ReceiptDAOImpl receiptDAO;
    private static SaleDAOImpl saleDAO;

    public static WarehouseDAOImpl getWarehouseDAO() {
        if (warehouseDAO == null) {
            warehouseDAO = new WarehouseDAOImpl();
        }
        return warehouseDAO;
    }

    public static ProductDAOImpl getProductDAO() {
        if (productDAO == null) {
            productDAO = new ProductDAOImpl();
        }
        return productDAO;
    }

    public static MovementDAOImpl getMovementDAO() {
        if (movementDAO == null) {
            movementDAO = new MovementDAOImpl();
        }
        return movementDAO;
    }

    public static ReceiptDAOImpl getReceiptDAO() {
        if (receiptDAO == null) {
            receiptDAO = new ReceiptDAOImpl();
        }
        return receiptDAO;
    }

    public static SaleDAOImpl getSaleDAO() {
        if (saleDAO == null) {
            saleDAO = new SaleDAOImpl();
        }
        return saleDAO;
    }

}
