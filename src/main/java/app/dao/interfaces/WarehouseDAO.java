package app.dao.interfaces;

import app.entities.Warehouse;

import java.util.List;

public interface WarehouseDAO {
    void addWarehouse(Warehouse warehouse);
    void updateWarehouse(Warehouse warehouse);
    Warehouse getWarehousetById(Long warehouse_id);
    List<Warehouse> getAllWarehouses();
    void deleteWarehouse(Warehouse warehouse);
}
