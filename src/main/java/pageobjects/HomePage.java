package pageobjects;

import support.Driver;

public class HomePage extends Driver{

    public static final String HOME_PAGE = "https://www.amazon.co.uk";

    public HomePage(){
        super(aDriver);
    }


    public HomePage gotoAmazon() {
        aDriver.navigate().to(HOME_PAGE);
        return new HomePage();
    }


    public String pageTitle(){
        String pageTitle = aDriver.getTitle();
        return pageTitle;
    }

}

