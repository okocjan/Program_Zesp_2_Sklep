package com.example.demo.app.model.responses;

import com.example.demo.app.model.entity.DiscountCode;
import com.example.demo.app.tools.GenericResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DiscountCodeResponse extends GenericResponse {

    @JsonProperty(value = "discountCodes")
    List<DiscountCode> discountCodes;

    private DiscountCodeResponse(boolean success, DiscountCode order) {
        super(success);
        this.discountCodes = Collections.singletonList(order);
    }

    private DiscountCodeResponse(boolean success) {
        super(success);
    }

    private DiscountCodeResponse(boolean success, Set<DiscountCode> discountCodes) {
        super(success);
        this.discountCodes = discountCodes.stream().toList();
    }

    private DiscountCodeResponse(boolean success, List<String> messages) {
        super(success, messages);
    }

    public static DiscountCodeResponse success(DiscountCode discountCode) {
        return new DiscountCodeResponse(true, discountCode);
    }

    public static DiscountCodeResponse success(Set<DiscountCode> discountCodes) {
        return new DiscountCodeResponse(true, discountCodes);
    }

    public static DiscountCodeResponse success() {
        return new DiscountCodeResponse(true);
    }

    public static DiscountCodeResponse failed(BindingResult result) {
        Set<String> messages = new HashSet<>();
        result.getAllErrors().forEach(e -> messages.add(e.getDefaultMessage()));
        return new DiscountCodeResponse(false, messages.stream().toList());
    }

    public static DiscountCodeResponse failed(List<String> messages) {
        return new DiscountCodeResponse(false, messages);
    }

    public static DiscountCodeResponse failed(String message) {
        return new DiscountCodeResponse(false, Collections.singletonList(message));
    }

}
