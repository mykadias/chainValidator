package com.myka.chainvalidator.service;

import com.myka.chainvalidator.model.DigitNode;
import com.myka.chainvalidator.model.LetterNode;
import com.myka.chainvalidator.model.Node;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

class NodeValidatorServiceTest {


    @ParameterizedTest
    @MethodSource({"getDigitNodesDatasource", "getLetterNodesDatasource", "simpleScenario"})
    void validation_givenDifferentInputs(List<Node<?>> digitNodeList, boolean expected) {
        // arrange
        NodeValidatorService nodeValidatorService = new NodeValidatorService();

        // act
        boolean validationResult = nodeValidatorService.validate(digitNodeList);

        // assert
        assertThat(validationResult)
            .isEqualTo(expected);
    }

    private static Stream<Arguments> getLetterNodesDatasource() {
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

    private static Stream<Arguments> getDigitNodesDatasource() {
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

    /**
     * Valid chain: 36, 6, 24, 4, 47, 7, 2, 3, 27
     * Valid chain: “p”, “aba”, ”a”, ”b”, “perso”, “o”, “r”, “e”, “s”
     * Not valid chain: 35, 5, 65, 6, 24, 4, (number 2 is missing).
     * @return
     */
    private static Stream<Arguments> simpleScenario() {


        return Stream.of(
            of(
                List.of(
                    new DigitNode(36),
                    new DigitNode(6),
                    new DigitNode(24),
                    new DigitNode(4),
                    new DigitNode(47),
                    new DigitNode(7),
                    new DigitNode(2),
                    new DigitNode(3),
                    new DigitNode(27)
                ),
                true
            ),
            of(
                List.of(
                    new LetterNode("p"),
                    new LetterNode("aba"),
                    new LetterNode("a"),
                    new LetterNode("b"),
                    new LetterNode("perso"),
                    new LetterNode("o"),
                    new LetterNode("e"),
                    new LetterNode("r"),
                    new LetterNode("s")),
                true
            ),
            of(
                List.of(
                    new DigitNode(35),
                    new DigitNode(5),
                    new DigitNode(65),
                    new DigitNode(6),
                    new DigitNode(24),
                    new DigitNode(4)
                ),
                false
            )
        );
    }


}