package quickus.core;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class QuickusSettings {
    protected final Collection<Object> stepCandidates = new LinkedList<>();
    protected final Collection<Object> featureCandidates = new LinkedList<>();

    public Collection<Object> getStepCandidates() {
        return stepCandidates;
    }

    public Collection<Object> getFeatureCandidates() {
        return featureCandidates;
    }

    public QuickusSettings withSteps(Object... steps) {
        return withSteps(List.of(steps));
    }

    public QuickusSettings withSteps(Collection<Object> steps) {
        stepCandidates.addAll(steps);
        return this;
    }

    public QuickusSettings withFeatures(Object... features) {
        return withFeatures(List.of(features));
    }

    public QuickusSettings withFeatures(Collection<Object> features) {
        featureCandidates.addAll(features);
        return this;
    }
}