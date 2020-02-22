package app.dao.interfaces;

import app.entities.Product;
import app.entities.Warehouse;

import java.util.List;

public interface ProductDAO {
    void addProduct(Product product);
    void updateProduct(Product product);
    Product getProductById(Long product_id);
    List<Product> getAllProducts();
    void deleteProduct(Product product);
    List<Product> getProductsByWarehouse(Warehouse warehouse);
}
