package quickus.core;

import quickus.annotations.FeatureDescription;

import static quickus.core.Feature.feature;

class OneFeature {
    protected final SomeSteps someSteps;

    public OneFeature(SomeSteps someSteps) {
        this.someSteps = someSteps;
    }

    @FeatureDescription("I should walk and run")
    public Feature shouldWalkAndRun() {
        return feature()
                .given(someSteps.walk, 3, 0.5)
                .when(someSteps.run, 1, 0.1)
                .then(someSteps.run, 5, 0.5);
    }

    @FeatureDescription("I should only walk")
    public Feature shouldWalk() {
        return feature()
                .given(someSteps.walk, 5, 5);
    }
}
