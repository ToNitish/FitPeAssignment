package FitPeo.Automation.TestCases;

import FitPeo.Automation.PageFactory.HomePage;
import FitPeo.Automation.PageFactory.RevenueCalculatorPage;
import FitPeo.Automation.TestData.AssignmentTaskData;
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

    @AfterMethod
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
           scrollToElementInCenter(revenueCalculatorPage.getSliderButton());
           Assert.assertTrue(isElementDisplayed(revenueCalculatorPage.getSliderButton()));
        }catch (Exception e) {

            Reporter.log("Test_03_ScrollDownToSliderSectionIsVisible is Failing ",true);
            Reporter.log(e.getMessage(),true);
            Assert.fail();

        }
    }

    @Test(priority = 4,enabled = true,dataProvider = "Value",dataProviderClass = AssignmentTaskData.class)
    public void Test_04_AdjustSliderTo820InPutFiled(String valueData){
        try{

           RevenueCalculatorPage revenueCalculatorPage=new RevenueCalculatorPage(driver);
           clickElement(revenueCalculatorPage.getRevenueCalculatorTab());
           scrollToElementInCenter(revenueCalculatorPage.getSliderButton());
           moveSliderToExactValue(revenueCalculatorPage.getInputOfSlider(),Integer.parseInt(valueData));
           String ValueOfSlider=revenueCalculatorPage.getInputOfSlider().getAttribute("value");
           Assert.assertEquals(valueData,ValueOfSlider);

        }catch (Exception e) {
            Reporter.log("Test_04_AdjustSliderTo820InPutFiled is Failing ",true);
            Reporter.log(e.getMessage(),true);
            Assert.fail();
        }
    }

    @Test(priority = 5,enabled = true,dataProvider = "InputData",dataProviderClass = AssignmentTaskData.class)
    public void Test_05_UpdateInputFieldTo560AndCheckSlideBar(String InputData){
        try{

            RevenueCalculatorPage revenueCalculatorPage=new RevenueCalculatorPage(driver);
            clickElement(revenueCalculatorPage.getRevenueCalculatorTab());
            scrollToElementInCenter(revenueCalculatorPage.getDotInputField());
            passTextToInputField(revenueCalculatorPage.getInputField(),InputData);

        }catch (Exception e) {
            Reporter.log("Test_05_UpdateInputFieldTo560AndCheckSlideBar is Failing ",true);
            Reporter.log(e.getMessage(),true);
            Assert.fail();
        }
    }

    @Test(priority = 6,enabled = true,dataProvider = "SliderValues",dataProviderClass = AssignmentTaskData.class)
    public void Test_06_validateSlider(String Target,String Minimum,String Maximum){
        try {

            RevenueCalculatorPage revenueCalculatorPage=new RevenueCalculatorPage(driver);
            clickElement(revenueCalculatorPage.getRevenueCalculatorTab());
            scrollToElementInCenter(revenueCalculatorPage.getDotInputField());
            moveSliderToValue(revenueCalculatorPage.getSliderTracker(),revenueCalculatorPage.getSliderHandler(),Integer.parseInt(Target),Integer.parseInt(Minimum),Integer.parseInt(Maximum));
            scrollToElementInCenter(revenueCalculatorPage.CPT99091());
            String PatientPerMonthText=getText(revenueCalculatorPage.getAmountTotalIndividualPatientPerMonth());
            String PersonInNavigation=getText(revenueCalculatorPage.getTotalIndividualPatientPerMonthAmountInNavigation());
            Assert.assertEquals(PatientPerMonthText,PersonInNavigation);

        }catch (Exception e) {
            Reporter.log("Test_06_validateSlider is Failing ",true);
            Reporter.log(e.getMessage(),true);
            Assert.fail();
        }
    }

    @Test(priority = 7,enabled = true,dataProvider = "SliderValues",dataProviderClass = AssignmentTaskData.class)
    public void Test_07_SelectCPTCode(String Target,String Minimum,String Maximum){
        try{
            RevenueCalculatorPage revenueCalculatorPage=new RevenueCalculatorPage(driver);
            clickElement(revenueCalculatorPage.getRevenueCalculatorTab());
            scrollToElementInCenter(revenueCalculatorPage.getDotInputField());
            moveSliderToValue(revenueCalculatorPage.getSliderTracker(),revenueCalculatorPage.getSliderHandler(),Integer.parseInt(Target),Integer.parseInt(Minimum),Integer.parseInt(Maximum));

            scrollToElementInCenter(revenueCalculatorPage.CPT99091());
            clickElement(revenueCalculatorPage.CPT99091());
            scrollToElementInCenter(revenueCalculatorPage.CPT99453());
            clickElement(revenueCalculatorPage.CPT99453());
            scrollToElementInCenter(revenueCalculatorPage.CPT99454());
            clickElement(revenueCalculatorPage.CPT99454());
            scrollToElementInCenter(revenueCalculatorPage.CPT99474());
            clickElement(revenueCalculatorPage.CPT99474());

            Assert.assertTrue(isCheckBoxIsSelected(revenueCalculatorPage.CPT99091()));
            Assert.assertTrue(isCheckBoxIsSelected(revenueCalculatorPage.CPT99474()));
            Assert.assertTrue(isCheckBoxIsSelected(revenueCalculatorPage.CPT99453()));
            Assert.assertTrue(isCheckBoxIsSelected(revenueCalculatorPage.CPT99454()));

        }catch (Exception e) {
            Reporter.log("Test_07_SelectCPTCode is Failing ",true);
            Reporter.log(e.getMessage(),true);
            Assert.fail();
        }
    }

    @Test(priority = 8,enabled = true,dataProvider = "SliderValues",dataProviderClass = AssignmentTaskData.class)
    public void Test_08_ValidateTotalRecurringReimbursement(String Target,String Minimum,String Maximum){
        try{

            RevenueCalculatorPage revenueCalculatorPage=new RevenueCalculatorPage(driver);
            clickElement(revenueCalculatorPage.getRevenueCalculatorTab());
            scrollToElementInCenter(revenueCalculatorPage.getDotInputField());
            moveSliderToValue(revenueCalculatorPage.getSliderTracker(),revenueCalculatorPage.getSliderHandler(),Integer.parseInt(Target),Integer.parseInt(Minimum),Integer.parseInt(Maximum));

            scrollToElementInCenter(revenueCalculatorPage.CPT99091());
            clickElement(revenueCalculatorPage.CPT99091());
            scrollToElementInCenter(revenueCalculatorPage.CPT99453());
            clickElement(revenueCalculatorPage.CPT99453());
            scrollToElementInCenter(revenueCalculatorPage.CPT99454());
            clickElement(revenueCalculatorPage.CPT99454());
            scrollToElementInCenter(revenueCalculatorPage.CPT99474());
            clickElement(revenueCalculatorPage.CPT99474());

            String TotalRecurringReimbursementForAllPatientsPerMonthAmountInNavigation=revenueCalculatorPage.getTotalRecurringReimbursementForAllPatientsPerMonthAmountInNavigation().getText();
            System.out.println("TotalRecurringReimbursementForAllPatientsPerMonthAmountInNavigation "+TotalRecurringReimbursementForAllPatientsPerMonthAmountInNavigation);
            Assert.assertEquals(TotalRecurringReimbursementForAllPatientsPerMonthAmountInNavigation,"$110970");

        }catch (Exception e) {
            Reporter.log("Test_08_ValidateTotalRecurringReimbursement is Failing ",true);
            Reporter.log(e.getMessage(),true);
            Assert.fail();
        }
    }

    @Test(priority = 9,enabled = true,dataProvider = "SliderData",dataProviderClass = AssignmentTaskData.class)
    public void Test_09_VerifyHeaderAndTotalRecurringReimbursementPatientsPerMonth(String Target,String Minimum,String Maximum,String Amount){
        try{
            RevenueCalculatorPage revenueCalculatorPage=new RevenueCalculatorPage(driver);
            clickElement(revenueCalculatorPage.getRevenueCalculatorTab());
            scrollToElementInCenter(revenueCalculatorPage.getDotInputField());
            moveSliderToValue(revenueCalculatorPage.getSliderTracker(),revenueCalculatorPage.getSliderHandler(),Integer.parseInt(Target),Integer.parseInt(Minimum),Integer.parseInt(Maximum));
            scrollToElementInCenter(revenueCalculatorPage.CPT99091());
            clickElement(revenueCalculatorPage.CPT99091());
            scrollToElementInCenter(revenueCalculatorPage.CPT99453());
            clickElement(revenueCalculatorPage.CPT99453());
            scrollToElementInCenter(revenueCalculatorPage.CPT99454());
            clickElement(revenueCalculatorPage.CPT99454());
            scrollToElementInCenter(revenueCalculatorPage.CPT99474());
            clickElement(revenueCalculatorPage.CPT99474());

            String TotalRecurringReimbursementForAllPatientsPerMonthAmountInNavigation=revenueCalculatorPage.getTotalRecurringReimbursementForAllPatientsPerMonthAmountInNavigation().getText();
            Assert.assertEquals(TotalRecurringReimbursementForAllPatientsPerMonthAmountInNavigation,Amount);

        }catch (Exception e) {
            Reporter.log("Test_09_VerifyHeaderAndTotalRecurringReimbursementPatientsPerMonth is Failing ",true);
            Reporter.log(e.getMessage(),true);
            Assert.fail();
        }

    }

}
