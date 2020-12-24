package ru.itis.models;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {
    private Integer id;
    private String originalFileName;
    private String storageFileName;
    private String type;
}
