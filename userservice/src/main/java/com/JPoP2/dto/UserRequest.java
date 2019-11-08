package com.megaproject.dto;

import lombok.Data;

@Data
public class UserRequest {
    private Long userId;
    private String userName;
    private Integer age;
    private String userEmailId;
}
