package com.turchanova.testProject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.util.List;

@Entity
@Table(name = "painting")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Painting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DecimalMin(value = "0.00")
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @DecimalMin(value = "0.00", message = "Значение должно быть 0 или больше нуля.")
    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "gallery_id")
    private Gallery gallery;

    @ManyToMany
    private List<Author> authorList;
}
