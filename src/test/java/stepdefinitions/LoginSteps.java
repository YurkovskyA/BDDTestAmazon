package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pageobjects.LoginPage;
import static org.hamcrest.CoreMatchers.*;

public class LoginSteps {

    LoginPage loginPage = new LoginPage();

    @Given("^User Signin using \"([^\"]*)\" and Password \"([^\"]*)\"$")
    public void userSigninUserAndPassword(String username,String password){

        Assert.assertEquals(loginPage.gotoSignInPage(), is(true));
        loginPage.userLoginWith(username,password);


    }

    @Then("^User Should Signin Successful$")
    public void successfulSignin(){
        Assert.assertEquals(
                loginPage.signedInUser().
                        pageTitle(),
                not("Amazon Sign In"));


    }

    @Then("^Notification Message should be \"([^\"]*)\"$")
    public void messageShouldBeDisplayed(String message) throws Throwable {

        Assert.assertEquals(loginPage.errorMessages(),
                hasItem(message));

    }


}
