package runners;

import api.services.WireMockApiService;
import io.cucumber.java.AfterAll;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps", "api"},
        tags = "@Test",
        plugin = {
                "pretty",
                "html:build/cucumber-reports.html",
                "json:build/cucumber.json"
        }
)
public class TestRunner {
        @AfterAll
        public static void afterAll() {
                WireMockApiService.tearDownWireMockServer();
        }
}