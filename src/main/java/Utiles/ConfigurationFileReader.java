package Utiles;

import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigurationFileReader {

    private static final Logger logger = Logger.getLogger(ConfigurationFileReader.class.getName());
    private final Properties properties;

    // Constructor to load the properties file
    public ConfigurationFileReader() {
        String propertyFilePath = ".//Configuration.properties";
        try (FileInputStream inputStream = new FileInputStream(propertyFilePath)) {
            properties = new Properties();
            properties.load(inputStream);
            logger.info("Configuration file loaded successfully from: " + propertyFilePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Configuration.properties file not found at: " + propertyFilePath, e);
            throw new RuntimeException("Configuration.properties file not found at: " + propertyFilePath, e);
        }
    }

    // Getter method for TestEnv
    public String getTestEnvironment() {
        try {
            return properties.getProperty("TestEnv", "Local");
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error reading TestEnv property. Returning default value: Local", e);
            return "Local";
        }
    }

    // Getter method for Browser
    public String getBrowser() {
        try {
            return properties.getProperty("Browser", "Chrome");
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error reading Browser property. Returning default value: Chrome", e);
            return "Chrome";
        }
    }

    // Getter method for PageURL
    public String getPageURL() {
        try {
            return properties.getProperty("PageURL");
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error reading PageURL property.", e);
            return null;
        }
    }

    // Getter method for UserName
    public String getUserName() {
        try {
            return properties.getProperty("UserName");
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error reading UserName property.", e);
            return null;
        }
    }

    // Getter method for Password
    public String getPassword() {
        try {
            return properties.getProperty("Password");
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error reading Password property.", e);
            return null;
        }
    }

    //Getter Method for TimeoutInSec
    public String getTimeOutInSec() {
        try {
            return properties.getProperty("TimeOutInSec");
        } catch (Exception e) {
            Reporter.log("Error in TimeOut In properties Files ",true);
            return "30";
        }
    }


}
