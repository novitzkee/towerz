package engine.utils;

@FunctionalInterface
public interface ThrowingRunnable {
    void run() throws Exception;
}