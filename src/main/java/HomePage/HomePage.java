package HomePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;

public class HomePage {
    WebDriver driver;

   // @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-backpack']")
    @FindBy(xpath = "//*[@id='add-to-cart-sauce-labs-backpack']")
    private WebElement backpack;

    @FindBy(xpath = "//div//a[@class='shopping_cart_link']")
    private WebElement cart;

    @FindBy(xpath = "//*[@id='item_4_title_link']/div")
    private WebElement itemname;

    @FindBy(xpath = "//*[@id='inventory_container']/div/div[1]/div[2]/div[1]/div")
    private WebElement itemDescription;

    @FindBy(xpath = "//*[@id='inventory_container']/div/div[1]/div[2]/div[2]/div")
    private WebElement itemPrice;

    @FindBy(xpath = "//*[@id='remove-sauce-labs-backpack']")
    private WebElement removeBtn;

    public HomePage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }

    public ArrayList addToCart()
    {
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(this.itemname.getText());
        arrayList.add(this.itemDescription.getText());
        arrayList.add(this.itemPrice.getText());
        this.backpack.click();
        arrayList.add(this.removeBtn.isDisplayed());
        this.cart.click();

        return arrayList;
    }

    public Boolean removebutton() throws InterruptedException {
        this.removeBtn.click();
        Thread.sleep(1000);
        Boolean addToCartBtn = this.backpack.isDisplayed();
        this.cart.click();
        return addToCartBtn;
    }

}
