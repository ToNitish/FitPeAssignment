package FitPeo.Automation.TestData;

import org.testng.annotations.DataProvider;

public class AssignmentTaskData {

    @DataProvider(name = "Value")
    public Object[][] Test04Data(){
        return new Object[][]{{"820"}};
    }

    @DataProvider(name = "InputData")
    public Object[][] Test05Data(){
        return new Object[][]{{"520"}};
    }

    @DataProvider(name = "SliderValues")
    public Object[][] Test06Data(){
        return new Object[][]{{"824","0","2000"}};
    }

    @DataProvider(name = "SliderData")
    public Object[][] Test09Data(){
        return new Object[][]{{"820","0","2000","$110160"}};
    }


}
