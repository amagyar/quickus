package app;

import quickus.core.QuickusAutomation;
import quickus.core.QuickusSettings;

public class App extends QuickusAutomation {

    SomeSteps someSteps = new SomeSteps();
    OneFeature oneFeature = new OneFeature(someSteps);

    public static void main(String[] args) {
        QuickusAutomation.run(App.class, args);
    }

    @Override
    public QuickusSettings settings() {
        return new QuickusSettings()
                .withSteps(someSteps)
                .withFeatures(oneFeature);
    }
}
