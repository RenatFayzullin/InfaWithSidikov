package ru.itis.dto;

import lombok.*;

@Data
public class AddCarDto {
    private String title;
    private String description;
    private Integer price;
    private String model;
    private String country;
}
