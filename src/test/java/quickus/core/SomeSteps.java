package quickus.core;

import quickus.annotations.Step;

class SomeSteps {
    protected Bean bean = new Bean();

    @Step("I walk ${miles} miles in ${hours} hours")
    public QuickusStep walk = (ctx) -> {
        int miles = ctx.getParameter("miles", Integer.class);
        bean.name = "Test";
        System.out.println("quickus.app.Bean name " + bean.name);
    };

    @Step("I run ${miles} miles in ${hours} hours")
    public QuickusStep run = (ctx) -> {
        int miles = ctx.getParameter("miles", Integer.class);
        System.out.println("quickus.app.Bean name " + bean.name);
        bean.name = "Boo-ya";
    };
}
