package com.example.demo.app.controller;

import com.example.demo.app.model.dto.ProductPersistDto;
import com.example.demo.app.model.entity.Product;
import com.example.demo.app.service.IProductService;
import com.example.demo.app.model.dto.projection.ProductListDto;
import com.example.demo.app.model.dto.projection.ProductPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;

@Controller
@RequestMapping("/app/data/product")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list.json")
    public ResponseEntity<Set<ProductListDto>> getProductsToView() {
        return new ResponseEntity<>(productService.getAllProductsWithPicture(), HttpStatus.OK);
    }

    @GetMapping("/getProduct.json")
    public ResponseEntity<ProductPageDto> getProductPage(@RequestParam(name = "id") Long id) {
        ProductPageDto result = productService.getProductPage(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product result = productService.addProduct(product);
        return Objects.isNull(result)
                ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product result = productService.updateProduct(product);
        return Objects.isNull(result)
                ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteProduct(@RequestBody(required = false) Product product,
                                                 @RequestParam(required = false) Long id) {
        if (Objects.isNull(product) && Objects.isNull(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Boolean result = productService.deleteProduct(product, id);
        return result
                ? new ResponseEntity<>(result, HttpStatus.OK)
                : new ResponseEntity<>(false, HttpStatus.OK);
    }


}
