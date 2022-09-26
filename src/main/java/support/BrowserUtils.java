package support;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserUtils extends Driver {

    public BrowserUtils(){
        super(aDriver);
    }

    public BrowserUtils clickWeBElement( WebElement element){
         new WebDriverWait(aDriver, Duration.ofSeconds(5)).
                until(ExpectedConditions.visibilityOf(element));
        element.click();
        return this;
    }

  public BrowserUtils clickLink( String link){
         WebElement element= new WebDriverWait(aDriver,Duration.ofSeconds(5)).
                until(ExpectedConditions.visibilityOfElementLocated(By.linkText(link)));
        element.click();
        return this;
    }

    public String setText(WebElement textField, String text){
      textField = new WebDriverWait(this.aDriver,Duration.ofSeconds(5)).
                until(ExpectedConditions.visibilityOf(textField));
        textField.clear();
        textField.sendKeys(text);
        return textField.getText();
    }

    public static void selectListOption(WebElement element, String option){
        element = new WebDriverWait(aDriver,Duration.ofSeconds(5)).
                until(ExpectedConditions.visibilityOf(element));
        Select options = new Select(element);
        options.selectByVisibleText(option);
    }

    public static String selectedOption(WebElement element){
        element = new WebDriverWait(aDriver, Duration.ofSeconds(5)).
                until(ExpectedConditions.visibilityOf(element));
        Select options = new Select(element);
        String selectedOption=null;
        try {
           selectedOption= options.getFirstSelectedOption().getText().toString();
        }
        catch (StaleElementReferenceException e){
        }
        return selectedOption;
    }


}
