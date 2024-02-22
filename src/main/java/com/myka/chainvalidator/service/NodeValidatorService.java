package com.myka.chainvalidator.service;

import com.myka.chainvalidator.model.DigitNode;
import com.myka.chainvalidator.model.LetterNode;
import com.myka.chainvalidator.model.Node;
import com.myka.chainvalidator.validator.DigitValidator;
import com.myka.chainvalidator.validator.LetterValidator;
import com.myka.chainvalidator.validator.NodeValidator;

import java.util.List;
import java.util.function.Function;

public class NodeValidatorService {

    private final NodeValidator<DigitNode> digitValidator = new DigitValidator();
    private final NodeValidator<LetterNode> letterValidator = new LetterValidator();


    public boolean validate(List<Node<?>> chain) {
        if(chain == null || chain.isEmpty()) {
            return true;
        }
        var firstNode = chain.get(0);

        return switch (firstNode.getClass().getSimpleName()) {
            case "DigitNode" -> digitValidator.validate(castToType(chain, DigitNode.class::cast));
            case "LetterNode" -> letterValidator.validate(castToType(chain, LetterNode.class::cast));
            default -> throw new IllegalStateException("Unexpected value: " + firstNode.getClass().getName());
        };
    }

    private static <T extends Node<?>> List<T> castToType(List<Node<?>> chain, Function<Node<?>, T> cast) {
        return chain.stream().map(cast).toList();
    }

}
