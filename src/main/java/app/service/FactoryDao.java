package app.service;

import app.dao.impl.MovementDAOImpl;
import app.dao.impl.ProductDAOImpl;
import app.dao.impl.ReceiptDAOImpl;
import app.dao.impl.SaleDAOImpl;
import app.dao.impl.WarehouseDAOImpl;
import app.dao.interfaces.CommonDAO;

public class FactoryDao {

    private static final ProductDAOImpl productDAO = new ProductDAOImpl();
    private static final WarehouseDAOImpl warehouseDAO = new WarehouseDAOImpl();
    private static final MovementDAOImpl movementDAO = new MovementDAOImpl();
    private static final ReceiptDAOImpl receiptDAO = new ReceiptDAOImpl();
    private static final SaleDAOImpl saleDAO = new SaleDAOImpl();

    public enum DaoType {
        PRODUCT,
        WAREHOUSE,
        MOVEMENT,
        RECEIPT,
        SALE
    }

    public static CommonDAO getInstance(DaoType type) {
        switch (type){
            case PRODUCT:
                return productDAO;
            case MOVEMENT:
                return movementDAO;
            case WAREHOUSE:
                return warehouseDAO;
            case RECEIPT:
                return receiptDAO;
            case SALE:
                return saleDAO;
            default:
                return null;
        }

    }
}
