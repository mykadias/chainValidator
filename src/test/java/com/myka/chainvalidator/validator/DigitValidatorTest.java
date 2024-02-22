package com.myka.chainvalidator.validator;


import com.myka.chainvalidator.model.DigitNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

class DigitValidatorTest {

    @ParameterizedTest
    @MethodSource("datasource")
    public void testValid_givenDifferentInputs(List<DigitNode> digitNodeList, boolean expected) {
        // arrange
        DigitValidator digitValidator = new DigitValidator();

        // act
        boolean validationResult = digitValidator.validationWithSort(new ArrayList<>(digitNodeList));
        boolean validationWithSortResult = digitValidator.validationWithSort(new ArrayList<>(digitNodeList));


        // assert
        assertThat(validationResult)
            .isEqualTo(expected);
        assertThat(validationWithSortResult)
            .isEqualTo(expected);
    }


    public static Stream<Arguments> datasource() {
        return Stream.of(
            of(
                List.of(),
                true
            ),
            of(
                List.of(new DigitNode(1)),
                true
            ),
            of(
                List.of(new DigitNode(1), new DigitNode(2)),
                true
            ),
            of(
                List.of(new DigitNode(1), new DigitNode(2), new DigitNode(21)),
                true
            ),
            of(
                List.of(new DigitNode(21)),
                false
            ),
            of(
                List.of(new DigitNode(210)),
                false
            ),
            of(
                List.of(new DigitNode(3), new DigitNode(2), new DigitNode(21)),
                false
            ),
            of(
                List.of(new DigitNode(3), new DigitNode(2), new DigitNode(21), new DigitNode(23)),
                false
            )
        );
    }

}