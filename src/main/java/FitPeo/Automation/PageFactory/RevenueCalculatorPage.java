package FitPeo.Automation.PageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RevenueCalculatorPage {

    WebDriver driver;

    public RevenueCalculatorPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(how = How.XPATH,using = "(//header//div)[15]/a/div[contains(text(),'Revenue Calculator')]")
    private WebElement RevenueCalculatorTab;
    public WebElement getRevenueCalculatorTab(){
        return RevenueCalculatorTab;
    }


    @FindBy(how = How.XPATH,using = "//h5[contains(text(),'Total Gross Revenue Per Year')]")
    private WebElement TotalGrossRevenuePerYearHeading;
    public WebElement getTotalGrossRevenuePerYearHeading(){
        return TotalGrossRevenuePerYearHeading;
    }


    @FindBy(how = How.XPATH,using = "//div/h3")
    private WebElement TotalAmountInGrossTotal;
    public WebElement getTotalAmountInGrossTotal(){
        return TotalAmountInGrossTotal;
    }


    @FindBy(how = How.XPATH,using = "//div[contains(@class, 'MuiBox-root' )]//span[contains(@class,'MuiSlider-rail')]")
    private WebElement SliderButton;
    public WebElement getSliderButton(){
        return SliderButton;
    }

    @FindBy(how = How.CSS,using = "input[type='range']")
    private WebElement InputOfSlider;
    public WebElement getInputOfSlider(){
        return InputOfSlider;
    }

    @FindBy(how = How.XPATH,using = "(//div//p[text()='CPT-99091']//following::label[contains(@class,'MuiFormControlLabel-root')]//input)[1]")
    private WebElement CPT99091;
    public WebElement CPT99091(){
        return CPT99091;
    }

    @FindBy(how = How.XPATH,using = "(//div//p[text()='CPT-99453']//following::label[contains(@class,'MuiFormControlLabel-root')]//input)[1]")
    private WebElement CPT99453;
    public WebElement CPT99453(){
        return CPT99453;
    }
    @FindBy(how = How.XPATH,using = "(//div//p[text()='CPT-99454']//following::label[contains(@class,'MuiFormControlLabel-root')]//input)[1]")
    private WebElement CPT99454;
    public WebElement CPT99454(){
        return CPT99454;
    }

    @FindBy(how = How.XPATH,using = "(//div//p[text()='CPT-99474']//following::label[contains(@class,'MuiFormControlLabel-root')]//input)[1]")
    private WebElement CPT99474;
    public WebElement CPT99474(){
        return CPT99474;
    }

    @FindBy(how = How.XPATH,using = "//div[contains(@class, 'MuiToolbar-root')]/p[text()='Total Gross Revenue Per Year:']/p")
    private WebElement TotalGrossRevenueInNavigation;
    public WebElement getTotalGrossRevenueInNavigation(){
        return TotalGrossRevenueInNavigation;
    }

    @FindBy(how = How.XPATH,using = "//div[contains(@class, 'MuiToolbar-root')]/p[text()='One Time Reimbursement for all Patients Annually:']/p")
    private WebElement OneTimeTotalReimbursementForAllPatientsAnnuallyAmountInNavigation;
    public WebElement getOneTimeTotalReimbursementForAllPatientsAnnuallyAmountInNavigation(){
        return OneTimeTotalReimbursementForAllPatientsAnnuallyAmountInNavigation;
    }

    @FindBy(how = How.XPATH,using = "//div[contains(@class, 'MuiToolbar-root')]/p[text()='Total Individual Patient/Month:']/p")
    private WebElement TotalIndividualPatientPerMonthAmountInNavigation;
    public WebElement getTotalIndividualPatientPerMonthAmountInNavigation(){
        return TotalIndividualPatientPerMonthAmountInNavigation;
    }

    @FindBy(how = How.XPATH,using = "//div[contains(@class, 'MuiToolbar-root')]/p[text()='Total Recurring Reimbursement for all Patients Per Month:']/p")
    private WebElement TotalRecurringReimbursementForAllPatientsPerMonthAmountInNavigation;
    public WebElement getTotalRecurringReimbursementForAllPatientsPerMonthAmountInNavigation(){
        return TotalRecurringReimbursementForAllPatientsPerMonthAmountInNavigation;
    }


    @FindBy(how = How.CSS,using = "input[type='range']")
    private WebElement DotInputField;
    public WebElement getDotInputField(){
        return DotInputField;
    }

    @FindBy(how = How.XPATH,using = "//span[contains(@class,'MuiSlider-root')]")
    private WebElement SliderTracker;
    public WebElement getSliderTracker(){
        return SliderTracker;
    }

    @FindBy(how = How.XPATH,using = "//span[contains(@class,'MuiSlider-thumb')]")
    private WebElement SliderHandler;
    public WebElement getSliderHandler(){
        return SliderHandler;
    }

    @FindBy(how = How.XPATH,using = "(//div[contains(@class,'MuiBox-root')]//p[contains(text(),'Total Individual Patient/Month')]/following-sibling::p)[2]")
    private WebElement AmountTotalIndividualPatientPerMonth;
    public WebElement getAmountTotalIndividualPatientPerMonth(){
        return AmountTotalIndividualPatientPerMonth;
    }


    public WebElement getInputField(){
        return (WebElement) By.id(":R57alklff9da:");
    }


}
