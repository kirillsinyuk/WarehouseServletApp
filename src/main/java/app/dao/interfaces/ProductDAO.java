package app.dao.interfaces;

import app.entities.Product;

import java.util.List;

public interface ProductDAO {
    void addProduct(Product product);
    void updateProduct(Product product);
    Product getProductById(Long product_id);
    List<Product> getAllProducts();
    void deleteProduct(Long product_id);
    List<Product> getProductsByWarehouse(Long warehouse_id);
}
