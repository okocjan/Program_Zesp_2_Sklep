package com.example.demo.app.controller;

import com.example.demo.app.model.dto.ProductPersistDto;
import com.example.demo.app.model.dto.ProductUpdateDto;
import com.example.demo.app.model.entity.Product;
import com.example.demo.app.model.responses.ProductResponse;
import com.example.demo.app.service.IProductService;
import com.example.demo.app.tools.Constants;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

import static com.example.demo.app.model.responses.ProductResponse.failed;
import static com.example.demo.app.model.responses.ProductResponse.success;

@Controller
@RequestMapping("/products")
@Api(tags = "Products", description = "Products API")
@CrossOrigin
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ProductResponse> getAll() {
        Set<Product> result = productService.getAllProducts();
        return !result.isEmpty()
                ? new ResponseEntity<>(success(result), HttpStatus.OK)
                : new ResponseEntity<>(failed(Constants.NO_PRODUCTS_FOUND_MESSAGE), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable(name = "id") Long id) {
        Optional<Product> result = productService.getProductById(id);
        return result
                .map(product -> new ResponseEntity<>(success(product), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(failed(Constants.ELEMENT_NOT_FOUND_MESSAGE), HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<ProductResponse> addProduct(@ModelAttribute @Valid ProductPersistDto productPersistDto,
                                                      BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(failed(result), HttpStatus.BAD_REQUEST);
        }
        Product toReturn = productService.addProduct(productPersistDto);
        return new ResponseEntity<>(success(toReturn), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody @Valid ProductUpdateDto product,
                                                         BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(failed(result), HttpStatus.BAD_REQUEST);
        }
        Product toReturn = productService.updateProduct(product);
        return new ResponseEntity<>(success(toReturn), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ProductResponse> deleteProduct(@RequestParam Long id) {
        Boolean result = productService.deleteProduct(id);
        return result
                ? new ResponseEntity<>(success(), HttpStatus.OK)
                : new ResponseEntity<>(failed(Constants.ELEMENT_NOT_FOUND_MESSAGE), HttpStatus.NOT_FOUND);
    }

}
