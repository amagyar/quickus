package app;

import quickus.core.QuickusStep;
import quickus.core.Step;

class SomeSteps {
    protected Bean bean = new Bean();

    @Step("I walk ${miles} miles in ${hours} hours")
    public QuickusStep walk = (ctx) -> {
        int miles = ctx.getParameter("miles", Integer.class);
        bean.name = "Test";
        System.out.println("app.Bean name " + bean.name);
    };

    @Step("I run ${miles} miles in ${hours} hours")
    public QuickusStep run = (ctx) -> {
        int miles = ctx.getParameter("miles", Integer.class);
        System.out.println("app.Bean name " + bean.name);
        bean.name = "Boo-ya";
    };
}
