package CucumberReport;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.En;

public class cucumberReport implements En {

    private int budget = 0;

    @Before
    public void up() throws InterruptedException {
        System.out.println("Taking Viagra");
    }

    @After
    public void down() throws InterruptedException {
        System.out.println("Viagra Finished");
    }



    public cucumberReport() {

        Given("^User is on Home Page$", () -> {
        });

        When("^User Navigate to LogIn Page$", () -> {
        });

        When("^User enters UserName and Password$", () -> {
        });

        Then("^Message displayed Login Successfully$", () -> {
        });

        When("^User LogOut from the Application$", () -> {
        });

        Then("^Message displayed LogOut Successfully$", () -> {
        });
    }

  
}
