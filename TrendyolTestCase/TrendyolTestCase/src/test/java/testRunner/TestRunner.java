package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"stepDefinitaions"},
        features = {"/Users/KA018/Desktop/TY2/TrendyolTestCase/TrendyolTestCase/features"}
)

public class TestRunner {
    @BeforeClass
    public static void before() {
        System.out.println("Before:\t" + System.currentTimeMillis());
    }

    @AfterClass
    public static void after() {
        System.out.println("After:\t" + System.currentTimeMillis());
    }
}
