package ru.itis.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class CarDto {
    private Integer id;
    private String title;
    private String description;
    private Integer price;
    private String model;
    private Integer markLogoId;
    private String country;
}
