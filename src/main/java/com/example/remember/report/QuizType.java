package com.example.remember.report;

public enum QuizType {
    MEMORY("기억력"),
    ATTENTION("주의력"),
    LOGICAL("논리력");

    private final String label;

    QuizType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
