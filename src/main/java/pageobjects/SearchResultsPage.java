package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ByAll;
import support.BrowserUtils;
import support.Driver;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zsuleiman on 04/02/2017.
 */
public class SearchResultsPage extends Driver {

    String a = "";
    @FindBy(how = How.ID, using = "noResultsTitle")
    public static WebElement noSearchResult;

    @FindBy(how = How.CSS, using = "ul#s-results-list-atf li h2")
    public static List<WebElement> resultsList;

    @FindBy(how = How.CSS, using = "#s-result-count .a-text-normal")
    public static List<WebElement> filters;

    @FindBy(how = How.ID, using = "sort")
    public static WebElement sortBy;

    @FindBy(how = How.CSS, using = "#leftNavContainer ul:first-of-type a")
    public static List<WebElement> categoryFilters;


    private Set<String> titleLists = new HashSet<>();

    public SearchResultsPage() {
        super(aDriver);
        PageFactory.initElements(Driver.aDriver, this);
    }

    public boolean addTitle(final String newString) {
        return this.titleLists.add(newString);
    }

    public Set<String> getTitles() {
        return Collections.unmodifiableSet(this.titleLists);
    }


    public Set<String> getResultTitles() {
        for (WebElement result : resultsList) {
            addTitle(result.getAttribute("textContent").toString());
        }
        return getTitles();
    }

    public boolean zeroSearchResults() {
        return noSearchResult.isDisplayed();
    }

    public String searchErrorMessage() {
        return noSearchResult.getAttribute("textContent");
    }

    public SearchResultsPage filterByCategory(String category) {
        try {
            for (WebElement aCategory : categoryFilters) {
                if (aCategory.getText().contains(category)) {
                    aCategory.click();
                }
            }
        }
        catch (StaleElementReferenceException e) {
        }
        return this;
    }

    public boolean resultFilteredBy(String filter) {
        for (WebElement aFilter : filters) {
            if (aFilter.getText().contains(filter)) {
                return true;
            }
        }
        return false;
    }

    public void sortBy(String option) {
        BrowserUtils.selectListOption(sortBy, option);
    }

    public String sortedBy() {
        return BrowserUtils.selectedOption(sortBy).toString();
    }
}
