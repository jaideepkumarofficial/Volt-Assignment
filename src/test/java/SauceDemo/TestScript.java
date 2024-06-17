package SauceDemo;

import Constant.WebUrls;
import Login_Page.Login_Page;
import Utilities.WebDriverUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestScript {
    Login_Page loginPage;
    WebDriver driver;
    @BeforeMethod
    public void start() throws InterruptedException {
        driver= WebDriverUtilities.chromeSetup(WebUrls.saucedemo);
        loginPage = new Login_Page(driver);
    }
    @Test
    public void trail()
    {
        loginPage.login();
    }

    @AfterMethod
    public void end() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}
