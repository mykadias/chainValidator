package com.myka.chainvalidator.model;

public class LetterNode implements Node<String>{

    private final String value;

    public LetterNode(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
