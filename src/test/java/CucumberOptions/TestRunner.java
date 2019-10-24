package CucumberOptions;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\java\\Features", glue = "StepDefinition")
public class TestRunner extends AbstractTestNGCucumberTests {

}
