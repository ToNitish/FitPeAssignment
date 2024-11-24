# Automation Assignment for Fitpe

This repository contains the solution for the automation assignment, where I have automated various scenarios using the **Page Object Model (POM)** design pattern. The assignment was successfully completed, meeting all the requirements provided.

## Project Overview

- **Objective**: Automate several scenarios as assigned, covering both functional and non-functional requirements.
- **Approach**: Implemented **POM (Page Object Model)** for a scalable and maintainable automation framework.

### Key Highlights:
1. **POM Design Pattern**:
    - **Modular** structure using PageFactory for page locators.
    - Clear separation of concerns between test cases and page actions.

2. **Configuration Management**:
    - Defined important variables in the `Configuration.properties` file under the `Util` package.
    - Developed a `ConfigurationReader` class to read the data from the properties file and make it accessible to the test scripts.

3. **Base Class**:
    - Implemented a base class under the `Util` package to handle common functions like:
        - Browser initiation.
        - Browser closure.
        - Click actions, input methods, waiting mechanisms, etc.

4. **Fitpe.Automation Package**:
    - Created individual packages for **PageFactory**, **TestCases**, and **TestData**.
        - **PageFactory**: Contains page locators.
        - **TestCases**: The actual test cases for different functionalities.
        - **TestData**: Any additional data required for testing, such as dynamic test data or configurations.

5. **Test Suite**:
    - Created a **TestSuite.xml** file to run all the test cases sequentially in a structured manner.
    - Ensured smooth execution of tests in a single run.

6. **Maven Integration**:
    - Used **Maven** for dependency management and project build lifecycle.
    - Integrated the `TestSuite.xml` with Maven to execute the tests in the desired sequence.

## How to Run the Project

1. Clone the repository:
   ```bash
   git clone <repository-url>
2. Navigate to the project directory:
   ```bash
   git cd <project-directory>
3. Execute the Maven command to run the tests:
   ```bash
   mvn clean test -DsuiteXmlFile=TestSuite.xml
   
4. The tests will be executed in the specified sequence as defined in **TestSuite.xml**

## Technologies Used

- **Selenium WebDriver**: For automating web browsers.
- **TestNG**: For running test cases and reporting.
- **Maven**: For dependency management and project build.
- **Page Object Model (POM)**: For structuring test code.

## Directory Structure

```plaintext
src/
├── main/
│   ├── java/
│   │   ├── fitpe/
│   │   │   ├── Automation/
│   │   │   │   ├── PageFactory/
│   │   │   │   │   ├── HomePage.java
│   │   │   │   │   └── RevenueCalculatorPage.java
│   │   │   │   ├── TestCases/
│   │   │   │   │   └── AssignmentTask.java
│   │   │   │   ├── TestData/
│   │   │   │   │   └── AssignmentTaskData.java
│   │   │   ├── Utilities/
│   │   │   │   ├── ConfigurationFileReader.java
│   │   │   │   └── Base.java
├── Configuration.properties
├── TestSuit/
├── pom.xml

```

## Conclusion

This repository showcases the implementation of the **Page Object Model (POM)** design pattern, ensuring clean, maintainable, and reusable automation code. The framework is optimized for scalability, allowing easy adaptation for larger projects while adhering to best practices in automation.

Feel free to fork this repository and contribute improvements or suggestions for further enhancements!
