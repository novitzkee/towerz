package engine;

import engine.time.Delay;
import engine.time.ExecutorServiceLoop;
import engine.time.Loop;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ExecutorServiceLoopTests {

    private static Stream<Arguments> tickTestArguments() {
        return Stream.of(
                Arguments.of(10, 30),
                Arguments.of(10, 500),
                Arguments.of(10, 1000),
                Arguments.of(20, 60),
                Arguments.of(20, 500),
                Arguments.of(20, 1000));
    }

    @ParameterizedTest
    @MethodSource("tickTestArguments")
    public void shouldTickWithGivenMillisecondDelay(int delayMillis, int runTimeMillis) throws Exception {
        final int expectedTicksCount = runTimeMillis / delayMillis;
        final TickCounter tickCounter = new TickCounter();

        final Loop loop = new ExecutorServiceLoop(Delay.ofMilliseconds(delayMillis));
        loop.add(tickCounter);
        loop.start();

        Thread.sleep(runTimeMillis);

        final int actualTicksCount = tickCounter.getTicks();
        assertThat(actualTicksCount).isCloseTo(expectedTicksCount, Offset.offset(2));
    }

    @Test
    public void shouldStopStartedLoop() throws Exception {
        final TickCounter tickCounter = new TickCounter();
        final ExecutorServiceLoop loop = new ExecutorServiceLoop(Delay.ofMilliseconds(20));
        loop.add(tickCounter);

        loop.start();
        Thread.sleep(100);
        final int ticksAfterStarting = tickCounter.getTicks();

        loop.stop();
        tickCounter.reset();
        Thread.sleep(100);
        final int ticksAfterStopping = tickCounter.getTicks();

        assertThat(ticksAfterStarting).isGreaterThan(0);
        assertThat(ticksAfterStopping).isZero();
    }

    @Test
    public void shouldStartStoppedLoop() throws Exception {
        final TickCounter tickCounter = new TickCounter();
        final ExecutorServiceLoop loop = new ExecutorServiceLoop(Delay.ofMilliseconds(20));
        loop.add(tickCounter);

        loop.start();
        Thread.sleep(100);
        final int ticksAfterStarting = tickCounter.getTicks();

        loop.stop();
        tickCounter.reset();
        Thread.sleep(100);
        final int ticksAfterStopping = tickCounter.getTicks();

        tickCounter.reset();
        loop.start();
        Thread.sleep(100);
        final int ticksAfterStartingStopped = tickCounter.getTicks();
        tickCounter.reset();

        assertThat(ticksAfterStarting).isGreaterThan(0);
        assertThat(ticksAfterStopping).isZero();
        assertThat(ticksAfterStartingStopped).isGreaterThan(0);
    }
}
