package com.myka.chainvalidator.validator;

import com.myka.chainvalidator.model.DigitNode;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DigitValidator implements NodeValidator<DigitNode>{

    @Override
    // Best approach - O(N)
    public boolean validate(List<DigitNode> nodes) {
        var valid = true;

        // HashSet lookups are O(1) on average
        Set<Integer> digitSet = new HashSet<>();

        // O(n)
        for (var digitNode : nodes) {
            if(digitNode.getValue() < 0 || digitNode.getValue() > 99) {
                return false;
            }
            if(digitNode.getValue() < 10){
                digitSet.add(digitNode.getValue()); // O(1)
            }
        }

        // O(n)
        for (var digitNode : nodes) {
            if(digitNode.getValue() >= 10) {
                int[] digits = getDigits(digitNode.getValue());
                //      O(1)                            O(1)
                valid = digitSet.contains(digits[0]) && digitSet.contains(digits[1]);
            }
            // O(1)
            if(!valid) {
                return false;
            }
        }

        // O(N + N + (1 + 1 + 1)N) == O(5N) == O(N)
        // O(N)
        return valid;
    }

    // Simplest approach - O(N Log N)
    public boolean validationWithSort(List<DigitNode> nodes) {
        var valid = true;

        // HashSet lookups are O(1) on average
        Set<Integer> digitSet = new HashSet<>();

        // O(n log n)
        nodes.sort(Comparator.comparingInt(DigitNode::getValue));

        // O(n)
        for (var digitNode : nodes) {
            if(digitNode.getValue() < 0 || digitNode.getValue() > 99) {
                return false;
            }

            if(digitNode.getValue() < 10){
                digitSet.add(digitNode.getValue()); // O(1)
            } else {
                int[] digits = getDigits(digitNode.getValue());
                //      O(1)                            O(1)
                valid = digitSet.contains(digits[0]) && digitSet.contains(digits[1]);
            }

            if(!valid) {
                return false;
            }
        }

        // O(1 + (n log n) + n(1 + 1)) == O(2N + NlogN + 1) == O(NlogN)
        // O(N log N)
        return valid;
    }

    private int[] getDigits(int number) {
        String numberString = Integer.toString(number);
        return new int[]{
            Integer.parseInt(numberString.substring(0, 1)),
            Integer.parseInt(numberString.substring(1, 2))
        };
    }
}
