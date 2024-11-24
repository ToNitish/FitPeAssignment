package FitPeo.Automation.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(how = How.XPATH,using = "(//button[contains(text(),'Contact Us')])[1]")
    private WebElement ContactUsButtonOnHomePage;
    public WebElement getContactUsButtonOnHomePage(){
        return ContactUsButtonOnHomePage;
    }

    @FindBy(how = How.XPATH,using = "//button[contains(text(),'Schedule A Demo')]")
    private WebElement ScheduleADemoButton;
    public WebElement getScheduleADemoButton(){
        return ScheduleADemoButton;
    }



}
