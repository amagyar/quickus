package quickus.core;

@FunctionalInterface
public interface QuickusStep {
    void run(QuickusContext ctx);
}
