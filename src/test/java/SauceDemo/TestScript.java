package SauceDemo;

import CartPage.CartPage;
import CheckOutPage.CheckoutPage;
import Constant.WebUrls;
import HomePage.HomePage;
import Login_Page.Login_Page;
import Utilities.WebDriverUtilities;
import com.beust.ah.A;
import com.sun.source.tree.AssertTree;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestScript {
    Login_Page loginPage;
    HomePage homePage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    WebDriver driver;
    @BeforeMethod
    public void start() throws InterruptedException {
        driver= WebDriverUtilities.chromeSetup(WebUrls.saucedemo);
        loginPage = new Login_Page(driver);
        homePage = new HomePage(driver);
        cartPage= new CartPage(driver);
        checkoutPage=new CheckoutPage(driver);
    }
    @Test
    public void checkFuntionality() throws InterruptedException {
        boolean sucessfullLogin = true;

        // Login Validation
        String result= loginPage.loginSucessful(sucessfullLogin,driver);
        Assert.assertEquals(result,WebUrls.homepage);
        System.out.println("Login Done");

        //Add to cart Validation
        ArrayList home;
        ArrayList cart;
        home=homePage.addToCart();
        cart=cartPage.cart();

        for(int i=0;i<3;i++)
        {
            Assert.assertEquals(home.get(i),cart.get(i));
        }
        Assert.assertTrue((Boolean) home.get(3));
        Assert.assertTrue((Boolean) cart.get(3));
        Assert.assertTrue((Boolean) cart.get(4));
        Assert.assertTrue((Boolean) cart.get(5));
        System.out.println("Add to Cart Functionality Done");


        //Remove Item validation from HomePage
        String url = cartPage.gotoHomepage(driver);
        Assert.assertEquals(url,WebUrls.homepage);
        Boolean addtoCart = homePage.removebutton();
        Assert.assertTrue(addtoCart);
        List items=cartPage.listedItem();
        Assert.assertEquals(items.size(),1);
        System.out.println("Remove Item from Homepage done");


        //Remove Button validation from CartPage
        cartPage.gotoHomepage(driver);
        homePage.addToCart();
        List item=cartPage.removeItemFromCartPage();
        Assert.assertEquals(item.size(),1);
        System.out.println("Remove Item from Cart Page");

        //Checkout Flow
        ArrayList cartItems;
        cartPage.gotoHomepage(driver);
        cartItems = homePage.addToCart();
        cartPage.clickOnCheck();
        ArrayList arrayList=checkoutPage.enterDetils("Jaideep","Kumar","560077");
        for(int i=0;i<3;i++)
        {
            Assert.assertEquals(arrayList.get(i),cartItems.get(i));
        }
        Assert.assertEquals(arrayList.get(3),"Thank you for your order!");
        Assert.assertEquals(arrayList.get(4),"Your order has been dispatched, and will arrive just as fast as the pony can get there!");
        Assert.assertTrue((Boolean) arrayList.get(5));

        System.out.println("CheckOut Flow is done");
    }

    @Test
    public void checkUnSucessfulLoginFuntionality() throws InterruptedException {
        boolean sucessfullLogin = false;
        String result= loginPage.loginSucessful(sucessfullLogin,driver);
        Assert.assertEquals(result,"Epic sadface: Username and password do not match any user in this service","Error Message is not as expected");
    }

    @AfterMethod
    public void end() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}
