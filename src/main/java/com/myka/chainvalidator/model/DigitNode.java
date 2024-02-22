package com.myka.chainvalidator.model;

public class DigitNode implements Node<Integer> {
    private final Integer value;

    public DigitNode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
