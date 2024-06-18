package CheckOutPage;

import CartPage.CartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class CheckoutPage {

    WebDriver driver;
    CartPage cartpage = new CartPage(driver);
    @FindBy(xpath = "//*[@id='first-name']")
    private WebElement FirstName;

    @FindBy(xpath = "//*[@id='last-name']")
    private WebElement LastName;

    @FindBy(xpath = "//*[@id='postal-code']")
    private WebElement ZipCode;

    @FindBy(xpath = "//*[@id='continue']")
    private WebElement ContinueBtn;


    @FindBy(xpath = "//*[@id='item_4_title_link']/div")
    private WebElement itemName;

    @FindBy(xpath = "//*[@id='checkout_summary_container']/div/div[1]/div[3]/div[2]/div[1]")
    private WebElement itemDesc;

    @FindBy(xpath = "//*[@id='finish']")
    private WebElement finish;

    @FindBy(xpath = "//*[@id='checkout_summary_container']/div/div[1]/div[3]/div[2]/div[2]/div")
    private WebElement price;

    @FindBy(xpath = "//*[@id='checkout_complete_container']/h2")
    private WebElement headerMsg;

    @FindBy(xpath = "//*[@id='checkout_complete_container']/div")
    private WebElement Msg;

    @FindBy(xpath = "//*[@id='back-to-products']")
    private WebElement backTohome;

    public CheckoutPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }

    public ArrayList enterDetils(String firstName, String lastName, String pincode) throws InterruptedException {
        //cartpage.clickOnCheck();Thread.sleep(1000);
        this.FirstName.sendKeys(firstName);
        this.LastName.sendKeys(lastName);
        this.ZipCode.sendKeys(pincode);
        this.ContinueBtn.click();
        ArrayList arrayList = checkOut();
        arrayList.add(this.headerMsg.getText());
        arrayList.add(this.Msg.getText());
        arrayList.add(this.backTohome.isDisplayed());


        return arrayList;
    }

    public ArrayList checkOut()
    {
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(this.itemName.getText());
        arrayList.add(this.itemDesc.getText());
        arrayList.add(this.price.getText());
        this.finish.click();
        return arrayList;
    }

}
