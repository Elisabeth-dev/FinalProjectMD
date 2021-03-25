package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "comparator_table")
public class ComparName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long compar_id;

    private String compar_name;

    @Column(columnDefinition = "TEXT")
    private String text_Comparator;
}
