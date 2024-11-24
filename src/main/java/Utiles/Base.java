package Utiles;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
            Thread.sleep(20);
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

    //Scroll the Element for the Specific Element is Not Visible
    public void scrollToElement(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Loop until the element is displayed
            while (!isElementDisplayed(element)) {
                // Scroll to the element and center it in the page view
                js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
            }

            Reporter.log("Element is now visible and centered on the page.", true);
        } catch (Exception e) {
            Reporter.log("Failed to scroll to the element: " + e.getMessage(), true);
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


    public void moveSliderTo(WebElement element, int inputValue) {
        try {
            // Get the current value of the slider
            String currentAttributeValue = element.getAttribute("value");
            int currentValue = Integer.parseInt(currentAttributeValue);

            // Define the maximum width of the slider (2000)
            int maxWidth = 2000; // Slider's maximum value (the total width)

            // Calculate the offset to move for each step (based on total width)
            int step = 2; // Adjust this step size as needed for finer control

            // Create Actions object once (not inside the loop)
            Actions action = new Actions(driver);

            // Move the slider until we reach the exact input value
            while (currentValue < inputValue) {
                action.clickAndHold(element)
                        .moveByOffset(step, 0)  // Move slider by a small step to the right
                        .release()
                        .perform();

                // Wait for the slider to update
                Thread.sleep(50);

                // Update the current value after the move
                currentAttributeValue = element.getAttribute("value");
                currentValue = Integer.parseInt(currentAttributeValue);

                // If the slider exceeds the target value, stop the loop
                if (currentValue >= inputValue) {
                    break;
                }
            }

            // Ensure the value is exactly the input value
            if (currentValue == inputValue) {
                Reporter.log("Slider moved to the exact value: " + inputValue, true);
            } else {
                Reporter.log("Failed to move slider to the exact value: " + inputValue, true);
            }
        } catch (Exception e) {
            // Log any exceptions that occur
            Reporter.log("Failed to move slider: " + e.getMessage(), true);
        }
    }



}
