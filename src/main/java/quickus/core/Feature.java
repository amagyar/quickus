package quickus.core;

import quickus.types.StepType;

import java.util.*;

public class Feature {
    protected final Collection<ExecutableStep> steps = new LinkedList<>();
    protected final QuickusContext ctx = new QuickusContext();

    public static Feature feature() {
        return new Feature();
    }

    @Deprecated
    public void report(String description) {
        System.out.println();
        System.out.println("----------");
        System.out.println(description);
        System.out.println("----------");
        System.out.println();
    }

    public Collection<ExecutableStep> getSteps() {
        return steps;
    }

    public Feature given(QuickusStep step, Object... params) {
        steps.add(new ExecutableStep(StepType.GIVEN, step, ctx, params));
        return this;
    }

    public Feature when(QuickusStep step, Object... params) {
        steps.add(new ExecutableStep(StepType.WHEN, step, ctx, params));
        return this;
    }

    public Feature then(QuickusStep step, Object... params) {
        steps.add(new ExecutableStep(StepType.THEN, step, ctx, params));
        return this;
    }
}

