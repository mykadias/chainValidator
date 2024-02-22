package com.myka.chainvalidator.validator;

import com.myka.chainvalidator.model.Node;

import java.util.List;

public interface NodeValidator <T extends Node<?>> {
    boolean validate(List<T> node);
}
