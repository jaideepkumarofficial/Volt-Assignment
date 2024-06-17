package HomePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }

}
