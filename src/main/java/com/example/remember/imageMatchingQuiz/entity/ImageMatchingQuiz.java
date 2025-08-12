package com.example.remember.imageMatchingQuiz.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "image_matching_quiz")
public class ImageMatchingQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, name = "key_0")
    private String key0;

    @NotBlank
    @Column(nullable = false, name = "key_1")
    private String key1;

    @NotBlank
    @Column(nullable = false, name = "key_2")
    private String key2;

    protected ImageMatchingQuiz() {
    }

    public ImageMatchingQuiz(String key0, String key1, String key2) {
        if (key0 == null || key0.isBlank() ||
                key1 == null || key1.isBlank() ||
                key2 == null || key2.isBlank()) {
            throw new IllegalArgumentException("모든 키는 null이거나 빈 값일 수 없습니다.");
        }
        this.key0 = key0;
        this.key1 = key1;
        this.key2 = key2;
    }

    public Long getId() {
        return id;
    }

    public String getKey0() {
        return key0;
    }

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }
}
