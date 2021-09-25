package quickus.core;

import quickus.annotations.FeatureDescription;
import quickus.annotations.Step;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class QuickusAutomation {
    public static <T> void run(Class<T> clazz, String[] args) {
        System.out.println("Starting application");
        try {
            // first find the steps candidates provided by settings
            T automationCandidate = clazz.getDeclaredConstructor().newInstance();

            if (automationCandidate instanceof QuickusAutomation) {
                System.out.println("Extension application");
                QuickusAutomation qa = QuickusAutomation.class.cast(automationCandidate);
                QuickusSettings settings = qa.settings();

                // gather all steps
                Map<String, Field> sentenceField = new HashMap<>();
                Map<QuickusStep, String>  stepSentence = new HashMap<>();
                Collection<Object> stepCandidates = settings.getStepCandidates();
                for (Object rawStep : stepCandidates) {
                    for (Field field : rawStep.getClass().getDeclaredFields()) {
                        if (field.isAnnotationPresent(Step.class)) {
                            String sentence = field.getAnnotation(Step.class).value();
                            field.setAccessible(true);
                            QuickusStep step = QuickusStep.class.cast(field.get(rawStep));
                            stepSentence.put(step, sentence);
                            sentenceField.put(sentence, field);
                        }
                    }
                }

                Collection<Object> featureCandidates = settings.getFeatureCandidates();
                for (Object rawFeature : featureCandidates) {
                    for (Method method : rawFeature.getClass().getDeclaredMethods()) {
                        method.setAccessible(true);
                        if (method.isAnnotationPresent(FeatureDescription.class)) {
                            FeatureDescription annotation = method.getAnnotation(FeatureDescription.class);
                            Feature feature = Feature.class.cast(method.invoke(rawFeature));
                            feature.report(annotation.value());
                            Collection<ExecutableStep> steps = feature.getSteps();
                            steps.stream().forEach(executableStep -> {
                                String sentence = stepSentence.get(executableStep.step());
                                executableStep.ctx.clearStepContext();
                                executableStep.execute(sentence);
                            });
                        }
                    }
                }
            }

            // check annotation
            // TODO
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public QuickusSettings settings() {
        return new QuickusSettings(); // TODO default settings
    }
}

