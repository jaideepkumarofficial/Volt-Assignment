package Login_Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Page {
    WebDriver driver;

    @FindBy(id = "login_credentials")
    private WebElement usernames;

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement enterusername;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement enterpassword;

    @FindBy(xpath = "//div[@class='login_password']")
    private WebElement passwords;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginbutton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement error;

    public Login_Page(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }

    public String[] getValue(WebElement idElement,WebElement passElement)
    {
        String[] id_pass=new String[2];
        String idText = idElement.getText();
        String passText = passElement.getText();
        String[] idLines = idText.split("\n");
        String[] passLines = passText.split("\n");

        id_pass[0]=idLines[1].trim();
        id_pass[1]=passLines[1].trim();

        return id_pass;
    }

    public String loginSucessful(Boolean flag, WebDriver driver) throws InterruptedException {
        String[] id_pass = getValue(usernames,passwords);
        String value = "";
        if(flag)
        {
            this.enterusername.sendKeys(id_pass[0]);
            this.enterpassword.sendKeys(id_pass[1]);
            this.loginbutton.click();
            value=driver.getCurrentUrl();
        }
        else
        {
            this.enterusername.sendKeys(id_pass[0]);
            this.enterpassword.sendKeys(id_pass[1].replace(id_pass[1].substring(id_pass[1].length()-1),""));
            this.loginbutton.click(); Thread.sleep(1000);
            value = this.error.getText();
        }


        return value;
    }

    public void loginFailed()
    {

    }

}
