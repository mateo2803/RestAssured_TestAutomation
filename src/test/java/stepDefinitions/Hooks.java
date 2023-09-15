package stepDefinitions;

import org.junit.Before;

public class Hooks {
    @Before
    public void beforeTest() throws Exception {
        Thread.sleep(60000);
    }
}
