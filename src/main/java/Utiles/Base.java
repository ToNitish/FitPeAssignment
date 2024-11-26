package Utiles;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import javax.swing.plaf.TableHeaderUI;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Base {

    private static final Logger logger = Logger.getLogger(Base.class.getName());

    protected WebDriver driver;
    private final Utiles.ConfigurationFileReader configReader;

    // Constructor to initialize the configuration reader
    public Base() {
        try {
            configReader = new ConfigurationFileReader();
            Reporter.log("Configuration File Reader initialized successfully.", true);
        } catch (Exception e) {
            Reporter.log("Failed to initialize Configuration File Reader: " + e.getMessage(), true);
            logger.log(Level.SEVERE, "Error initializing ConfigurationFileReader", e);
            throw new RuntimeException("Error initializing ConfigurationFileReader", e);
        }
    }

    // Method to initialize the WebDriver based on the browser
    public void initializeDriver() {
        String browser = configReader.getBrowser().toLowerCase();

        try {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    Reporter.log("ChromeDriver initialized successfully.", true);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    Reporter.log("FirefoxDriver initialized successfully.", true);
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    Reporter.log("EdgeDriver initialized successfully.", true);
                    break;
                default:
                    Reporter.log("Unsupported browser: " + browser, true);
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            driver.manage().window().maximize();
            Reporter.log("Browser window maximized.", true);
        } catch (Exception e) {
            Reporter.log("Failed to initialize WebDriver for browser: " + browser + " - " + e.getMessage(), true);
            throw new RuntimeException("Error initializing WebDriver for browser: " + browser, e);
        }
    }

    // Method to navigate to a specific URL
    public void navigateToURL(String url) {
        try {
            driver.get(url);
            Reporter.log("Navigated to URL: " + url, true);
        } catch (Exception e) {
            Reporter.log("Failed to navigate to URL: " + url + " - " + e.getMessage(), true);
            throw new RuntimeException("Error navigating to URL: " + url, e);
        }
    }

    // Method to close the browser
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
                Reporter.log("Browser closed successfully.", true);
            } catch (Exception e) {
                Reporter.log("Failed to close the browser: " + e.getMessage(), true);
            }
        }
    }

    //Wait Method for Elements
    public void waitForElementToBeVisible(WebElement element) {
        try {
            int timeoutInSeconds = Integer.parseInt(new ConfigurationFileReader().getTimeOutInSec());
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.visibilityOf(element)); // Wait until the element is visible
            Reporter.log("Element is visible: " + element.toString(), true);
        } catch (Exception e) {
            Reporter.log("Error waiting for element to be visible: " + element.toString() + " - " + e.getMessage(), true);
        }
    }


    public void waitForElementToBeClickable(WebElement element) {
        try {
            int timeoutInSeconds = Integer.parseInt(new ConfigurationFileReader().getTimeOutInSec());
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.elementToBeClickable(element)); // Wait until the element is clickable
            Reporter.log("Element is clickable: " + element.toString(), true);
        } catch (Exception e) {
            Reporter.log("Error waiting for element to be clickable: " + element.toString() + " - " + e.getMessage(), true);
        }
    }

    public void waitForElementToBePresent(WebElement locator) {
        try {
            int timeoutInSeconds = Integer.parseInt(new ConfigurationFileReader().getTimeOutInSec());
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.presenceOfElementLocated((By) locator)); // Wait until the element is present in the DOM
            Reporter.log("Element is present in DOM: " + locator.toString(), true);
        } catch (Exception e) {
            Reporter.log("Error waiting for element to be present in DOM: " + locator.toString() + " - " + e.getMessage(), true);
        }
    }



    // Common Action for Web Page
    public void clickElement(WebElement element) {
        try {
            waitForElementToBeClickable(element);
            element.click();
            Reporter.log("Clicked on element: " + element.toString(), true);
        } catch (Exception e) {
            Reporter.log("Error clicking on element: " + element.toString() + " - " + e.getMessage(), true);
        }
    }

    public void provideInput(WebElement element, String inputText) {
        try {
            waitForElementToBeVisible(element);
            element.click();
            element.clear();// Clear existing input if any
            element.sendKeys(inputText); // Enter the provided text
            Reporter.log("Entered text into element: " + element.toString() + " with value: " + inputText, true);
        } catch (Exception e) {
            Reporter.log("Error entering text into element: " + element.toString() + " - " + e.getMessage(), true);
        }
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            // Check if the element is displayed
            waitForElementToBeVisible(element);
            boolean isDisplayed = element.isDisplayed();

            // Log the result
            if (isDisplayed) {
                Reporter.log("Element is displayed: " + element.toString(), true);
            } else {
                Reporter.log("Element is not displayed: " + element.toString(), true);
            }

            return isDisplayed;
        } catch (Exception e) {
            // Log the error if element is not found or any other issue occurs
            Reporter.log("Error checking if element is displayed: " + element.toString() + " - " + e.getMessage(), true);
            return false; // Return false if an error occurs
        }
    }

    //Scroll Element to Visible in the Center
    public void scrollToElementInCenter(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Thread.sleep(2000);
            // Get the element's position and height
            js.executeScript(
                    "const element = arguments[0];" +
                            "const elementRect = element.getBoundingClientRect();" +
                            "const absoluteElementTop = elementRect.top + window.pageYOffset;" +
                            "const middle = absoluteElementTop - (window.innerHeight / 2);" +
                            "window.scrollTo({ top: middle, behavior: 'smooth' });",
                    element
            );

            Reporter.log("Element has been successfully brought to the center of the page.", true);
        } catch (Exception e) {
            Reporter.log("Failed to bring the element to the center: " + e.getMessage(), true);
        }
    }

    public void setSliderValue(WebElement slider, int targetValue) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Set the value directly using JavaScript
            js.executeScript(
                    "arguments[0].setAttribute('value', arguments[1]);" +
                            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                    slider,
                    targetValue
            );

            Reporter.log("Slider value set to: " + targetValue, true);
        } catch (Exception e) {
            Reporter.log("Failed to set slider value: " + e.getMessage(), true);
        }
    }

    public void moveSliderToExactValue(WebElement sliderElement, int targetValue) {
        try {
            // Use JavaScript to set the value of the slider directly
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Set the value to the target value
            js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                    sliderElement, targetValue);

            // Log the success
            Reporter.log("Slider moved to the exact value: " + targetValue, true);
        } catch (Exception e) {
            // Log any exceptions
            Reporter.log("Failed to move slider: " + e.getMessage(), true);
        }
    }

    public  void moveSliderToValue(WebElement sliderTrack, WebElement sliderHandle, int targetValue, int minValue, int maxValue) {

        // Slider track width
        int sliderWidth = sliderTrack.getSize().getWidth();
        System.out.println("Slider Width: " + sliderWidth);

        int initialValue = 200;  // Initial value of the slider (assuming it starts at 200)

        // Slider step size (can be 4 or whatever value the slider increments by)
        int stepSize = 1;

        // Initial and target percentages
        double initialPercentage = ((double) (initialValue - minValue) / (maxValue - minValue)) * 100;
        double targetPercentage = ((double) (targetValue - minValue) / (maxValue - minValue)) * 100;

        System.out.println("Initial Percentage: " + initialPercentage);
        System.out.println("Target Percentage: " + targetPercentage);

        // Calculate the offset (with more precision)
        double offsetX = ((targetPercentage - initialPercentage) / 100) * sliderWidth;
        // Round to the nearest multiple of stepSize
        int roundedOffsetX = (int) Math.round(offsetX / stepSize) * stepSize;

        System.out.println("Calculated OffsetX (Rounded to Step Size): " + roundedOffsetX);

        // Move slider using Actions
        Actions action = new Actions(driver);
        action.clickAndHold(sliderHandle)
                .moveByOffset(roundedOffsetX, 0)
                .release()
                .perform();

        // Wait until slider value is updated
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".slider-class")));

            // Retrieve the value using JavaScript
            String sliderValue = (String) ((JavascriptExecutor) driver)
                    .executeScript("return document.querySelector('.slider-class').value;");
            System.out.println("Slider Value Retrieved: " + sliderValue);

            // Ensure exact value by setting the slider directly through JavaScript
            int actualSliderValue = Integer.parseInt(sliderValue);
            System.out.println("Actual Slider Value: " + actualSliderValue);

            // If the slider is close to target but not exact, adjust manually
            if (Math.abs(actualSliderValue - targetValue) > 2) {
                System.out.println("Adjustment needed. Slider is too far from target value.");
                String script = "document.querySelector('.slider-class').value = " + targetValue + ";";
                ((JavascriptExecutor) driver).executeScript(script);
                System.out.println("Slider value set directly to: " + targetValue);
            }
        } catch (Exception e) {
            System.out.println("Error validating slider value: " + e.getMessage());
        }


    }

    public boolean isCheckBoxIsSelected(WebElement checkBoxElement) {
        boolean isSelected = false;

        try {
            // Check if the checkbox is selected
            isSelected = checkBoxElement.isSelected();

            // Log the result
            if (isSelected) {
                Reporter.log("Checkbox is selected.", true);
            } else {
                Reporter.log("Checkbox is not selected.", true);
            }
        } catch (Exception e) {
            // Catching any exceptions and logging the error
            Reporter.log("Error occurred while checking the checkbox status: " + e.getMessage(), true);
        }

        return isSelected;
    }

    public String getText(WebElement element) {
        String text = "";
        try {
            // Retrieve the text of the element
            text = element.getText();
            // Log the text value retrieved
            Reporter.log("Text retrieved from element: " + text, true);
        } catch (Exception e) {
            // Catch any exception and log the error message
            Reporter.log("Error occurred while retrieving text from the element: " + e.getMessage(), true);
        }
        return text;
    }

    public void jsscriptPassTextToElement(WebElement element, String textValue) {
        try {
            // Create a JavascriptExecutor instance
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

            // Use JavaScript to set the value of the element
            jsExecutor.executeScript("arguments[0].value = arguments[1];", element, textValue);

            // Log success message using Reporter
            Reporter.log("Text '" + textValue + "' successfully passed to element: " + element.toString(), true);

        } catch (Exception e) {
            // Handle any exception that occurs during the process
            Reporter.log("Failed to pass text to the element. Error: " + e.getMessage(), true);
        }
    }

    public void passTextToInputField(WebElement element, String textValue) {
        try {

            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            // Set the value of the input element using JavaScript
            jsExecutor.executeScript("arguments[0].value = arguments[1];", element, textValue);
            Reporter.log("Text '" + textValue + "' successfully passed to element: " + element.toString(), true);

        } catch (Exception e) {
            Reporter.log("Failed to pass text to the element. Error: " + e.getMessage(), true);
        }
    }

    public void clearAndPasteText(WebDriver driver, By locator, String textToPaste) {
        // Find the element using the provided locator
        WebElement inputElement = driver.findElement(locator);

        // Create an Actions object to simulate keyboard actions
        Actions actions = new Actions(driver);

        // Simulate Ctrl+A to select all text, then Backspace to delete it, and finally paste the new text
        actions.click(inputElement)                    // Click to focus on the input field
                .keyDown(Keys.CONTROL)                   // Hold down the Ctrl key
                .sendKeys("12")                           // Press 'A' to select all text
                .keyUp(Keys.CONTROL)                     // Release the Ctrl key
                .sendKeys(Keys.BACK_SPACE)               // Press Backspace to delete the selected text
                .sendKeys(textToPaste)                   // Type the new text to paste
                .build()
                .perform();                             // Perform the actions

        // Optionally, you can also use the clipboard to simulate paste, but it's simpler with this approach
    }


}
