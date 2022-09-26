package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.BrowserUtils;
import support.Driver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LoginPage extends Driver {


    @FindBy(how= How.ID,using = "nav-link-yourAccount")
    public static WebElement accountLink;

    @FindBy(how= How.ID,using = "ap_email")
    public static WebElement usernameField;

    @FindBy(how= How.ID,using = "ap_password")
    public static WebElement passwordField;

    @FindBy(how= How.ID,using = "signInSubmit")
    public static WebElement signInButton;

    @FindBy(how =How.XPATH,using= "//*[contains(@class,error-message)]")
    public static List<WebElement> errorNotifications;


    BrowserUtils browserUtils =new BrowserUtils();

    public LoginPage(){
        super(aDriver);
        PageFactory.initElements(aDriver,this);
    }

    public boolean gotoSignInPage() {
        browserUtils.clickWeBElement(accountLink);
        return  new WebDriverWait(aDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.titleContains("Sign In"));

    }

    public String setUserName(String username){
        return browserUtils.setText(usernameField,username);
    }

    public String setPassword(String password){
        return browserUtils.setText(passwordField,password);
    }


    public LoginPage userLoginWith(String username, String password){
        setUserName(username);
        setPassword(password);
        signInButton.submit();
    return this;

    }

    public HomePage signedInUser(){
        return new HomePage();
    }


    public List<String> errorMessages(){
        List<String> errors = new ArrayList<>();
        for(WebElement error: errorNotifications){
            errors.add(error.getText());
        }
        return errors;
    }
}
