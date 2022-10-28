package engine.time;

import org.assertj.core.data.Offset;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeScalerTests {

    private static Stream<Arguments> timeScalingTestArguments() {
        return Stream.of(
                Arguments.of(100, 0.1f),
                Arguments.of(100, 0.333f),
                Arguments.of(100, 0.666f),
                Arguments.of(100, 1f),
                Arguments.of(100, 0.777f),
                Arguments.of(100, 2f),
                Arguments.of(100, 1000f));
    }

    @ParameterizedTest
    @MethodSource("timeScalingTestArguments")
    public void shouldScaleTicks(int totalTickCount, float scale) {
        final int expectedTickCount = Math.round(totalTickCount * scale);
        final TickCounter tickCounter = new TickCounter();
        final TimeScaler scaledCounter = new TimeScaler(tickCounter);
        scaledCounter.setScale(scale);

        for(int i = 0; i < totalTickCount; i++) {
            scaledCounter.tick();
        }

        final int actualTickCount = tickCounter.getTicks();
        assertThat(actualTickCount).isCloseTo(expectedTickCount, Offset.offset(1));
    }
}
