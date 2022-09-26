package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageobjects.HomePage;
import pageobjects.SearchPage;
import pageobjects.SearchResultsPage;

public class SearchSteps  {

    private HomePage homePage = new HomePage();
    private SearchPage searchPage = new SearchPage();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();

    @Given("^User navigates to Amazon$")
    public void userNavigateToAmazon(){

       homePage.gotoAmazon();
    }

    @Then("^User should be on Amazon page$")
    public void pageShouldBeAmazon(){

        Assert.assertEquals(
                homePage.pageTitle().
                        contains("Amazon.co.uk"),
                true);

    }

    @Given("^User search for \"([^\"]*)\"$")
    public void userSearchFor(String searchKeyword){

        searchPage.searchKeyword(searchKeyword);
        searchPage.submitSearch();

    }

    @Then("^search results should contains \"([^\"]*)\"$")
    public void searchResultsShouldContain(String searchKeyword){
        Assert.assertEquals(
                searchResultsPage.
                getResultTitles().toString()
                .contains(searchKeyword),true);

    }

    @Then("^No results should be returned$")
    public void noSearchResultReturned(){

        Assert.assertEquals(
                searchResultsPage.
                        zeroSearchResults(),
                true);

    }

    @Then("^Message \"([^\"]*)\" should be displayed$")
    public void invalidSearchMessageShouldBeDisplayed(String message){

        Assert.assertEquals(searchResultsPage
                    .searchErrorMessage(),
                    message);

    }

    @When("^User Applies Category Filter \"([^\"]*)\"$")
    public void userFiltersByCategory(String category){

        searchResultsPage.filterByCategory(category);


    }

    @Then("^Results Should be Filtered with \"([^\"]*)\"$")
    public void resultsShouldBeFilteredBy(String filter){

        Assert.assertEquals(searchResultsPage.
                    resultFilteredBy(filter),true);

    }


    @Given("^Sort By \"([^\"]*)\"$")
    public void userSortBy(String sortBy){

        searchResultsPage.sortBy(sortBy);

    }

    @Then("^Result should be Sorted By \"([^\"]*)\"$")
    public void resultShouldBeSortedBy(String sortedBy){

        Assert.assertEquals(
                searchResultsPage.sortedBy(),
                sortedBy);
    }

}
