package com.example.demo.app.model.dto;

import com.example.demo.app.model.entity.custom.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusUpdateDto {

    private List<Long> idList;
    private Status status;

}
