package com.example.remember.RandomWordQuiz.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "word_generation_log")
public class WordGenerationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;
    private String lang;
    private Integer length;
    private String pos;

    @Column(length = 1000)
    private String prompt;
}
