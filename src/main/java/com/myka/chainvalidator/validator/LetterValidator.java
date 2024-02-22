package com.myka.chainvalidator.validator;

import com.myka.chainvalidator.model.LetterNode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LetterValidator implements NodeValidator<LetterNode>{
    @Override
    public boolean validate(List<LetterNode> chain) {
        var valid = true;

        Set<Character> letterSet = new HashSet<>();

        // O(n)
        for(var node : chain){
            if(node.getValue().length() == 1){
                // O(1)
                letterSet.add(node.getValue().charAt(0));
            }
        }

        // O(n)
        for(var node : chain){
            if(node.getValue().length() > 1){
                // O(n)
                for(var letter : node.getValue().toCharArray()){
                    // O(1)
                    valid = valid && letterSet.contains(letter);
                }
            }

            if(!valid) {
                return false;
            }
        }

        // O(N + (N * N)) == O(N + N^2) == O(N^2)
        // O(N^2)
        return valid;
    }
}
