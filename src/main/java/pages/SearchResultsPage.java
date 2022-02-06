package pages;

import elements.Element;
import elements.TextInput;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SearchResultsPage extends BasePage{

    @FindBy(xpath = "//div[@data-filter-name='producer']//input[@name='searchline']")
    private TextInput brandFilterSearchInput;

    @FindBy(xpath = "//div[@data-filter-name='producer']//a[@class='checkbox-filter__link']")
    private List<Element> brandFilterCheckboxesList;

    @FindBy(css = ".catalog-settings__sorting > select")
    private WebElement sortDropdown;

    @FindBy(xpath = "//div[@data-filter-name='sell_status']//a[@class='checkbox-filter__link']")
    private List<Element> sellStatusFilterCheckboxesList;

    @FindBy(css = "ul.catalog-grid > li:first-child a.goods-tile__heading")
    private WebElement firstProductTileHeadingLink;

    public SearchResultsPage() {
        super();
    }

    public void enterBrandNameInSearchInput(final String brandName){
        brandFilterSearchInput.enterTextInEmptyInput(brandName);
    }

    public void clickOnBrandFilterCheckbox(final String brandName) {
        int iteration = 1;
        while (true) {
            try {
                brandFilterCheckboxesList.stream()
                        .filter(x -> x.getAttribute("data-id").equals(brandName))
                        .findFirst().orElseThrow().click();
                break;
            } catch (Exception e) {
                if (iteration > 2){
                    throw e;
                }
                iteration++;
            }
        }

    }

    public void sortProductsByOption(final String optionValue){
        Select sortDropdownSelect = new Select(sortDropdown);
        waitUntilVisibilityOfElement(sortDropdown);
        sortDropdownSelect.selectByValue(optionValue);
    }

    public void showOnlyAvailableProducts(){
        int iteration = 1;
        while (true){
            try{
                sellStatusFilterCheckboxesList.stream()
                        .filter(x -> x.getAttribute("href")
                                .contains("sell_status=available"))
                        .findFirst().orElseThrow().click();
                break;
            } catch (Exception e){
                if (iteration > 2){
                    throw e;
                }
                iteration++;
            }
        }
    }

    public void clickOnFirstProduct(){
        int iteration = 1;
        while (true) {
            try {
                Actions actions = new Actions(driver);
                waitUntilVisibilityOfElement(firstProductTileHeadingLink);
                waitUntilElementIsClickable(firstProductTileHeadingLink);
                actions.moveToElement(firstProductTileHeadingLink).perform();
                firstProductTileHeadingLink.click();
                break;
            } catch(Exception e){
                if (iteration > 2){
                    throw e;
                }
                iteration++;
            }
        }
    }
}
