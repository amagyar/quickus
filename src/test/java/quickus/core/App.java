package quickus.core;

import org.testng.annotations.Test;

public class App extends QuickusAutomation {

    SomeSteps someSteps = new SomeSteps();
    OneFeature oneFeature = new OneFeature(someSteps);

    @Test
    public void runApp() {
        QuickusAutomation.run(App.class, null);
    }

    @Override
    public QuickusSettings settings() {
        return new QuickusSettings()
                .withSteps(someSteps)
                .withFeatures(oneFeature);
    }
}
