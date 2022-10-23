package com.example.demo.app.model.dto;

import com.example.demo.app.model.entity.Product;
import com.example.demo.app.model.entity.ProductPicture;
import com.example.demo.app.model.entity.Storage;

public class ProductPersistDto {

    private Product product;
    private ProductPicture productPicture;
    private Storage storage;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductPicture getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(ProductPicture productPicture) {
        this.productPicture = productPicture;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
