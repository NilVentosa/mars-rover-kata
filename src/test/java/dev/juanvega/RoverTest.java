package dev.juanvega;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class RoverTest {

    @Test
    public void correctInitialisation() {
        Rover rover = new Rover(new Rover.Point(2, 3), Rover.Orientation.N);

        Assertions.assertThat(rover.orientation).isEqualTo(Rover.Orientation.N);
        Assertions.assertThat(rover.point.x()).isEqualTo(2);
        Assertions.assertThat(rover.point.y()).isEqualTo(3);
    }

    @ParameterizedTest
    @MethodSource("turnLeftCases")
    public void testTurnLeft(Rover.Orientation initial, Rover.Orientation expected) {
        Rover rover = new Rover(new Rover.Point(2, 3), initial);
        rover.move("L");

        Assertions.assertThat(rover.orientation).isEqualTo(expected);
    }

    public static Stream<Arguments> turnLeftCases() {
        return Stream.of(Arguments.of(Rover.Orientation.N, Rover.Orientation.W),
                         Arguments.of(Rover.Orientation.W, Rover.Orientation.S),
                         Arguments.of(Rover.Orientation.S, Rover.Orientation.E),
                         Arguments.of(Rover.Orientation.E, Rover.Orientation.N));
    }

    @ParameterizedTest
    @MethodSource("turnRightCases")
    public void testTurnRight(Rover.Orientation initial, Rover.Orientation expected) {
        Rover rover = new Rover(new Rover.Point(2, 3), initial);
        rover.move("R");

        Assertions.assertThat(rover.orientation).isEqualTo(expected);
    }

    public static Stream<Arguments> turnRightCases() {
        return Stream.of(Arguments.of(Rover.Orientation.N, Rover.Orientation.E),
                         Arguments.of(Rover.Orientation.W, Rover.Orientation.N),
                         Arguments.of(Rover.Orientation.S, Rover.Orientation.W),
                         Arguments.of(Rover.Orientation.E, Rover.Orientation.S));
    }

    @ParameterizedTest
    @MethodSource("moveForwardCases")
    public void testMoveForward(Rover.Orientation orientation, Rover.Point finalPoint) {
        Rover rover = new Rover(new Rover.Point(2, 3), orientation);
        rover.move("F");

        Assertions.assertThat(rover.point).isEqualTo(finalPoint);
    }

    public static Stream<Arguments> moveForwardCases() {
        return Stream.of(Arguments.of(Rover.Orientation.W, new Rover.Point(1, 3)),
                         Arguments.of(Rover.Orientation.E, new Rover.Point(3, 3)),
                         Arguments.of(Rover.Orientation.N, new Rover.Point(2, 4)),
                         Arguments.of(Rover.Orientation.S, new Rover.Point(2, 2)));
    }

    @ParameterizedTest
    @MethodSource("moveBackwardCases")
    public void testMoveBackward(Rover.Orientation orientation, Rover.Point finalPoint) {
        Rover rover = new Rover(new Rover.Point(2, 3), orientation);
        rover.move("B");

        Assertions.assertThat(rover.point).isEqualTo(finalPoint);
    }

    public static Stream<Arguments> moveBackwardCases() {
        return Stream.of(Arguments.of(Rover.Orientation.W, new Rover.Point(3, 3)),
                         Arguments.of(Rover.Orientation.E, new Rover.Point(1, 3)),
                         Arguments.of(Rover.Orientation.N, new Rover.Point(2, 2)),
                         Arguments.of(Rover.Orientation.S, new Rover.Point(2, 4)));
    }

    @Test
    public void multipleSteps() {
        Rover rover = new Rover(new Rover.Point(2, 8), Rover.Orientation.N);
        rover.move("FFLBRR");

        Assertions.assertThat(rover.point).isEqualTo(new Rover.Point(3,10));
        Assertions.assertThat(rover.orientation).isEqualTo(Rover.Orientation.E);
    }

}
