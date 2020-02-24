package app.dao.interfaces;

import app.model.entities.Product;

import java.util.List;

public interface ProductDAO {
    void addProduct(Product product);
    void updateProduct(Product product);
    Product getProductById(Long product_id);
    List<Product> getAllProducts();
    List<Product> getAllProductsCount();
    void deleteProduct(Long product_id);
    List<Product> getProductsByWarehouse(Long warehouse_id);
    List<Product> getProductsByParam(String name, String value);
}
