package quickus.core;

import java.lang.reflect.AnnotatedType;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuickusContext {
    protected Map<String, Object> parsedParams = new HashMap<>();

    public void clearStepContext() {
        parsedParams.clear();
    }

    public void setParameter(String key, Object value) {
        if (parsedParams.containsKey(key)) {
            System.out.println("WARN: The step already contains a param named [" + key + "]");
        }
        parsedParams.put(key, value);
    }

    public <T> T getParameter(String parameter, Class<T> clazz) {
        return clazz.cast(parsedParams.get(parameter));
    }
}
