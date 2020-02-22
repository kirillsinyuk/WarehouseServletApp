package app.service;

import app.dao.impl.ProductDAOImpl;
import app.dao.impl.WarehouseDAOImpl;
import app.dao.interfaces.ProductDAO;
import app.dao.interfaces.WarehouseDAO;

public class FactoryDao {

    private static ProductDAO productDAO = null;
    private static WarehouseDAO warehouseDAO = null;

    private static FactoryDao instance = null;

    public static synchronized FactoryDao getInstance() {
        if (instance == null) {
            instance = new FactoryDao();
        }
        return instance;
    }

    public ProductDAO getProductDAO() {
        if (productDAO == null) {
            productDAO = new ProductDAOImpl();
        }
        return productDAO;
    }

    public WarehouseDAO getWarehouseDAO() {
        if (warehouseDAO == null) {
            warehouseDAO = new WarehouseDAOImpl();
        }
        return warehouseDAO;
    }
}
