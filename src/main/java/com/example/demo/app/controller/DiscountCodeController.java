package com.example.demo.app.controller;

import com.example.demo.app.model.dto.DiscountCodeDto;
import com.example.demo.app.model.entity.DiscountCode;
import com.example.demo.app.model.responses.DiscountCodeResponse;
import com.example.demo.app.service.DiscountCodeService;
import com.example.demo.app.tools.Constants;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.example.demo.app.model.responses.DiscountCodeResponse.failed;
import static com.example.demo.app.model.responses.DiscountCodeResponse.success;

@Controller
@RequestMapping("/discount")
@Api(tags = "Discounts", description = "Discounts API")
@CrossOrigin
public class DiscountCodeController {

    private final DiscountCodeService discountCodeService;

    public DiscountCodeController(DiscountCodeService discountCodeService) {
        this.discountCodeService = discountCodeService;
    }

    @GetMapping("{id}")
    public ResponseEntity<DiscountCodeResponse> findOne(@PathVariable Long id) {
        Optional<DiscountCode> result = discountCodeService.getById(id);
        return result
                .map(discount -> new ResponseEntity<>(success(discount), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(failed(Constants.ELEMENT_NOT_FOUND_MESSAGE), HttpStatus.NOT_FOUND));
    }

    @GetMapping("{code}")
    public ResponseEntity<DiscountCodeResponse> findByCode(@PathVariable String code) {
        Optional<DiscountCode> result = discountCodeService.getByCode(code);
        return result
                .map(discount -> new ResponseEntity<>(success(discount), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(failed(Constants.ELEMENT_NOT_FOUND_MESSAGE), HttpStatus.NOT_FOUND));
    }

    @PutMapping("/toogle")
    public ResponseEntity<DiscountCodeResponse> toggleCode(@RequestParam List<Long> ids) {
        boolean result = discountCodeService.toggleCode(ids);
        return result ? new ResponseEntity<>(success(), HttpStatus.OK)
                : new ResponseEntity<>(failed("Something went wrong."), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<DiscountCodeResponse> addDiscountCode(@RequestBody DiscountCodeDto discountCodeDto,
                                                                BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(DiscountCodeResponse.failed(result), HttpStatus.BAD_REQUEST);
        }
        DiscountCode toReturn = discountCodeService.addDiscountCode(discountCodeDto);
        return new ResponseEntity<>(success(toReturn), HttpStatus.OK);
    }
}
