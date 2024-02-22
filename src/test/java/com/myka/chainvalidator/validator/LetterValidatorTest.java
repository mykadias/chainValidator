package com.myka.chainvalidator.validator;


import com.myka.chainvalidator.model.LetterNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

class LetterValidatorTest {

    @ParameterizedTest
    @MethodSource("datasource")
    public void validation_givenDifferentInputs(List<LetterNode> letterNodeList, boolean expected) {
        // arrange
        LetterValidator letterValidator = new LetterValidator();

        // act
        boolean validationResult = letterValidator.validate(new ArrayList<>(letterNodeList));


        // assert
        assertThat(validationResult)
            .isEqualTo(expected);
    }


    public static Stream<Arguments> datasource() {
        return Stream.of(
            of(
                List.of(),
                true
            ),
            of(
                List.of(new LetterNode("a")),
                true
            ),
            of(
                List.of(new LetterNode("aa"), new LetterNode("a")),
                true
            ),
            of(
                List.of(new LetterNode("apa"), new LetterNode("a"), new LetterNode("p")),
                true
            ),
            of(
                List.of(new LetterNode("api"), new LetterNode("a"), new LetterNode("p")),
                false
            ),
            of(
                List.of(new LetterNode("api"), new LetterNode("a"), new LetterNode("p"), new LetterNode("y")),
                false
            )
        );
    }

}