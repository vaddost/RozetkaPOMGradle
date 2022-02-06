package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Checkbox extends Element {

    public Checkbox(WebElement webElement) {
        super(webElement);
    }

    public void check() {
        if (!super.isSelected()) {
            super.click();
        } else {
            System.out.println("Element is already checked!");
        }
    }

    public String getLabelValue(){
        return webElement.findElement(By.xpath("./following-sibling::label")).getText();
    }
}
