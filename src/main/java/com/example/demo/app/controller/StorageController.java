package com.example.demo.app.controller;

import com.example.demo.app.model.dto.StorageUpdateDto;
import com.example.demo.app.model.responses.StorageResponse;
import com.example.demo.app.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.example.demo.app.model.responses.StorageResponse.failed;
import static com.example.demo.app.model.responses.StorageResponse.success;

@Controller
@RequestMapping("/storages")
public class StorageController {

    private final IProductService productService;

    @Autowired
    public StorageController(IProductService productService) {
        this.productService = productService;
    }

    @PutMapping
    public ResponseEntity<StorageResponse> updateProductQuantity(@RequestBody @Valid StorageUpdateDto updateDto,
                                                                 BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(failed(result), HttpStatus.BAD_REQUEST);
        }
        boolean updateResult = productService.updateProductQuantity(updateDto);
        StorageResponse toRet = updateResult
                ? success()
                : failed("Something went wrong");
        return new ResponseEntity<>(toRet, updateResult ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
}
