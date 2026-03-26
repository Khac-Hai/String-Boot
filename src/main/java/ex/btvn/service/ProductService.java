package ex.btvn.service;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import ex.btvn.model.Product;

@Service
public class ProductService {
    private ArrayList<Product> products;

    public ProductService() {
        products = new ArrayList<>();
        Product p1 = new Product(0, "Nguyen Khac Hai", 1000);
        Product p2 = new Product(1, "Nguyen Khac B", 2000);
        Product p3 = new Product(2, "Nguyen Khac C", 3000);
        products.add(p1);
        products.add(p2);
        products.add(p3);
    }

    public ArrayList<Product> getAllProducts() {
        return products;
    }
}
