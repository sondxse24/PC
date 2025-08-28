package com.pc.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;
}
