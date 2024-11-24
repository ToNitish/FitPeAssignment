package FitPeo.Automation.TestCases;

import FitPeo.Automation.PageFactory.HomePage;
import FitPeo.Automation.PageFactory.RevenueCalculatorPage;
import Utiles.Base;
import Utiles.ConfigurationFileReader;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AssignmentTask extends Base {

    @BeforeMethod
    public void OpenBrowser(){
        initializeDriver();
        navigateToURL(new ConfigurationFileReader().getPageURL());
    }

    //@AfterMethod
    public void CloseBrowser(){
        tearDown();
    }

    @Test(priority = 1,enabled = true)
    public void Test_01_NavigateToHomePage(){
        try{

            Reporter.log("Test_01_NavigateToHomePage is open ",true);
            HomePage homePage=new HomePage(driver);
            Assert.assertTrue(isElementDisplayed(homePage.getContactUsButtonOnHomePage()));
            Assert.assertTrue(isElementDisplayed(homePage.getScheduleADemoButton()));

        } catch (Exception e) {
            Reporter.log("Test_01_NavigateToHomePage is Failing ",true);
            Reporter.log(e.getMessage(),true);
            Assert.fail();
        }
    }

    @Test(priority = 2,enabled = true)
    public void Test_02_NavigateToReVenueCalculatorPage(){
        try{

            Reporter.log("Test_02_NavigateToReVenueCalculatorPage",true);
            RevenueCalculatorPage revenueCalculatorPage=new RevenueCalculatorPage(driver);
            clickElement(revenueCalculatorPage.getRevenueCalculatorTab());
            Assert.assertTrue(isElementDisplayed(revenueCalculatorPage.getTotalGrossRevenuePerYearHeading()));

        }catch (Exception e) {
            Reporter.log("Test_02_NavigateToReVenueCalculatorPage is Failing ",true);
            Reporter.log(e.getMessage(),true);
            Assert.fail();
        }
    }

    @Test(priority = 3,enabled = true)
    public void Test_03_ScrollDownToSliderSectionIsVisible(){
        try{

           RevenueCalculatorPage revenueCalculatorPage=new RevenueCalculatorPage(driver);
           clickElement(revenueCalculatorPage.getRevenueCalculatorTab());
           scrollToElement(revenueCalculatorPage.getSliderButton());
           Assert.assertTrue(isElementDisplayed(revenueCalculatorPage.getSliderButton()));

        }catch (Exception e) {

            Reporter.log("Test_03_ScrollDownToSliderSectionIsVisible is Failing ",true);
            Reporter.log(e.getMessage(),true);
            Assert.fail();

        }
    }

    @Test(priority = 4,enabled = true)
    public void Test_04_AdjustSliderTo820InPutFiled(){
        try{

           RevenueCalculatorPage revenueCalculatorPage=new RevenueCalculatorPage(driver);
           clickElement(revenueCalculatorPage.getRevenueCalculatorTab());
           scrollToElement(revenueCalculatorPage.getSliderButton());
           moveSliderTo(revenueCalculatorPage.getInputOfSlider(),820);
           String ValueOfSlider=revenueCalculatorPage.getInputOfSlider().getAttribute("value");
           System.out.println("Your value is : "+ValueOfSlider);

        }catch (Exception e) {
            Reporter.log("Test_04_AdjustSliderTo820InPutFiled is Failing ",true);
            Reporter.log(e.getMessage(),true);
            Assert.fail();
        }
    }

    @Test(priority = 5,enabled = true)
    public void Test_05_UpdateInputFieldTo560AndCheckSlideBar(){
        try{
         RevenueCalculatorPage revenueCalculatorPage=new RevenueCalculatorPage(driver);
         clickElement(revenueCalculatorPage.getRevenueCalculatorTab());
         scrollToElement(revenueCalculatorPage.getSliderInput());

         provideInput(revenueCalculatorPage.getSliderInput(),"320");

         String TextValue= revenueCalculatorPage.getSliderInput().getAttribute("value");
         Assert.assertEquals("320",TextValue);

        }catch (Exception e) {
            Reporter.log("Test_05_UpdateInputFieldTo560AndCheckSlideBar is Failing ",true);
            Reporter.log(e.getMessage(),true);
            Assert.fail();
        }
    }

    @Test(priority = 6,enabled = true)
    public void Test_06_validateSlider(){
        try {

        }catch (Exception e) {
            Reporter.log("Test_06_validateSlider is Failing ",true);
            Reporter.log(e.getMessage(),true);
            Assert.fail();
        }
    }

    @Test(priority = 7,enabled = true)
    public void Test_07_SelectCPTCode(){
        try{

        }catch (Exception e) {
            Reporter.log("Test_07_SelectCPTCode is Failing ",true);
            Reporter.log(e.getMessage(),true);
            Assert.fail();
        }
    }

    @Test(priority = 8,enabled = true)
    public void Test_08_ValidateTotalRecurringReimbursement(){
        try{

        }catch (Exception e) {
            Reporter.log("Test_08_ValidateTotalRecurringReimbursement is Failing ",true);
            Reporter.log(e.getMessage(),true);
            Assert.fail();
        }
    }

    @Test(priority = 9,enabled = true)
    public void Test_09_VerifyHeaderAndTotalRecurringReimbursementPatientsPerMonth(){
        try{
            RevenueCalculatorPage revenueCalculatorPage=new RevenueCalculatorPage(driver);

        }catch (Exception e) {
            Reporter.log("Test_09_VerifyHeaderAndTotalRecurringReimbursementPatientsPerMonth is Failing ",true);
            Reporter.log(e.getMessage(),true);
            Assert.fail();
        }

    }

}
