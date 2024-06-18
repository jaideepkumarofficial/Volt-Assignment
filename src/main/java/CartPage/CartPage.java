package CartPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id='item_4_title_link']/div")
    private WebElement productName;

    @FindBy(xpath = "//*[@id='cart_contents_container']/div/div[1]/div[3]/div[2]/div[1]")
    private WebElement productDesc;

    @FindBy(xpath = "//*[@id='cart_contents_container']/div/div[1]/div[3]/div[2]/div[2]/div")
    private WebElement productPrice;

    @FindBy(xpath = "//*[@id='remove-sauce-labs-backpack']")
    private WebElement removeButton;

    @FindBy(xpath = "//*[@id='continue-shopping']")
    private WebElement continueShoppingBtn;

    @FindBy(xpath = "//*[@id='checkout']")
    private WebElement checkOutBtn;

    @FindBy(xpath = "//*[@id='cart_contents_container']/div/div[1]")
    private WebElement cartList;


    public CartPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }

    @FindBy(xpath = "//*[@id='cart_contents_container']/div/div[1]")
    private List<WebElement> listedIteams;



    public ArrayList cart()
    {
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(this.productName.getText());
        arrayList.add(this.productDesc.getText());
        arrayList.add(this.productPrice.getText());
        arrayList.add(this.removeButton.isDisplayed());
        arrayList.add(this.continueShoppingBtn.isDisplayed());
        arrayList.add(this.checkOutBtn.isDisplayed());

        return arrayList;
    }

    public String gotoHomepage(WebDriver driver)
    {
        String page_url = "";
        this.continueShoppingBtn.click();
        page_url = driver.getCurrentUrl();

        return page_url;
    }

    public List removeItemFromCartPage()
    {
        this.removeButton.click();
        List items = listedItem();

        return items;
    }

    public List listedItem()
    {
        List items = new ArrayList<>();
        items=this.listedIteams;

        return items;
    }

    public void clickOnCheck()
    {
        this.checkOutBtn.click();
    }

}
