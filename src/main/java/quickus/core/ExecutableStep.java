package quickus.core;

import quickus.types.StepType;

import java.util.Collection;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExecutableStep {
    protected static String paramNameRegex = "\\$\\{%s\\}";
    protected static String param = "\\$\\{(.*?)\\}";
    protected static Pattern paramPattern = Pattern.compile(param);

    protected final QuickusStep step;
    protected final StepType stepType;
    protected final QuickusContext ctx;
    protected final Object[] params;
    protected final Collection<String> paramNames = new LinkedList<>();

    public ExecutableStep(StepType stepType, QuickusStep step, QuickusContext ctx, Object[] params) {
        this.stepType = stepType;
        this.step = step;
        this.ctx = ctx;
        this.params = params;
    }

    public QuickusStep step() {
        return step;
    }

    public void setParamsToContext(String sentence) {
        Matcher matcher = paramPattern.matcher(sentence);
        int i = 0;
        while (matcher.find()) {
            String paramName = matcher.group(1);
            paramNames.add(paramName);
            if ((i+1) <= params.length) {
                ctx.setParameter(paramName, params[i++]);
            } else {
                throw new IllegalArgumentException("You've forgotten to pass the value for parameter [" + paramName + "]");
            }
        }
    }

    public void report(String sentence) {
        String parsedSentence = parseSentence(sentence);
        // TODO pass to reports
        System.out.println(stepType + " " + parsedSentence);
    }

    public String parseSentence(String sentence) {
        for (String paramName : paramNames) {
            String regex = String.format(paramNameRegex, paramName);
            sentence = sentence.replaceAll(regex, ctx.getParameter(paramName, Object.class).toString());
        };
        return sentence;
    }

    public void execute(String sentence) {
        setParamsToContext(sentence);
        report(sentence);
        step.run(ctx);
    }
}
