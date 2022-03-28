package com.example.exception.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Product {

    @Id
    private Long id;

    private String name;

    private Double price;

    private LocalDateTime manufacturingDate;


    private Double weight;

    @Embedded
    private Dimension dimension;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Embeddable
    @Getter
    @Setter
    public static class Dimension {
        private Double height;

        private Double width;
    }
}
