package ru.itis.models;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class Car {
    private Integer id;
    private String title;
    private String description;
    private Integer price;
    private String model;
    private Integer markLogoId;
    private String country;
}
