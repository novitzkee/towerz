package engine.traits;

public interface Removable {
    boolean isGarbage();

    default void cleanUp() { }
}
