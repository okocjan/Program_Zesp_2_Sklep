package com.example.demo.app.controller;

import com.example.demo.app.model.dto.ProductPersistDto;
import com.example.demo.app.model.dto.ProductUpdateDto;
import com.example.demo.app.model.dto.projection.ProductListDto;
import com.example.demo.app.model.dto.projection.ProductPageDto;
import com.example.demo.app.model.entity.Product;
import com.example.demo.app.service.IProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;

@Controller
@RequestMapping("/product")
@Api(tags = "Products", description = "Products API")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Set<ProductListDto>> getAll() {
        return new ResponseEntity<>(productService.getAllProductsWithPicture(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductPageDto> getProduct(@PathVariable(name = "id") Long id) {
        ProductPageDto result = productService.getProductPage(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductPersistDto product) {
        Product result = productService.addProduct(product);
        return Objects.isNull(result)
                ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody ProductUpdateDto product) {
        Product result = productService.updateProduct(product);
        return Objects.isNull(result)
                ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteProduct(@RequestParam Long id) {
        Boolean result = productService.deleteProduct(id);
        return result
                ? new ResponseEntity<>(result, HttpStatus.OK)
                : new ResponseEntity<>(false, HttpStatus.OK);
    }


}
