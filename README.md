# Project Name

WebMath Test Automation

## Table of Contents

- [Project Overview](#project-overview)
- [Installation](#installation)
- [Usage](#usage)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)

## Project Overview

The project contains automated test scripts for testing the functionality of the WebMath website. The tests are implemented using Selenium WebDriver, Java, and TestNG framework. The test scripts interact with the website to validate various functionalities and scenarios.

## Installation

### Clone the repository:

   ```shell
   git clone https://github.com/your-username/your-repo.git
```

1. **Set up the Java development environment:**

   - Install Java Development Kit (JDK)
   - Set up the `JAVA_HOME` environment variable

2. **Set up Selenium WebDriver:**

   - Download the appropriate WebDriver executable(s) for your desired browser(s) (e.g., ChromeDriver for Google Chrome)
   - Add the WebDriver executable(s) to your system's `PATH`

3. **Import the project into your preferred Java IDE:**

   - Eclipse:
     - File -> Import -> Existing Projects into Workspace
     - Select the project folder
     - Click Finish

   - IntelliJ IDEA:
     - File -> Open
     - Select the project folder
     - Click OK

4. **Resolve project dependencies:**

   - Configure the build path or use a dependency management tool like Maven or Gradle to download the required dependencies.

## Usage

1. **Configure the test properties:**

   - Open the `configuration.properties` file located in the project's resources directory
   - Update the properties with the desired values for your test scenarios (e.g., URLs, input data)

2. **Execute the tests:**

   - Run the test classes using the TestNG runner in your IDE or command line

   - Example command for running tests via TestNG XML file:

     ```shell
     mvn test -DsuiteXmlFile=testng.xml
     ```

3. **View the test reports:**

   - After executing the tests, the Extent Reports HTML report will be generated in the project's target directory
   - Open the generated HTML report in a web browser to view the detailed test execution results

## Configuration

The test configuration is managed through the `configuration.properties` file. Modify the properties in this file to customize the test behavior:

- `browser`: Specify the browser to use for test execution (e.g., chrome, firefox)
- `url`: Specify the URL of the WebMath website to be tested
- Add additional properties as needed for your specific test scenarios

## Manual Testing

In addition to automated tests, manual testing is also important to ensure the quality of the WebMath website. Follow these steps to perform manual testing:

1. **Test Scenarios:**

   - Identify the key features and functionalities of the WebMath website that need manual testing.
   - Document the test scenarios and expected results for each feature or functionality.

2. **Test Environment:**

   - Prepare the test environment with the required browser(s) and test data.

3. **Execute Manual Tests:**

   - Perform the manual tests based on the documented test scenarios.
   - Record the test results, including any issues or defects encountered.

4. **Report Issues:**

   - If any issues or defects are found during manual testing, report them in the issue tracking system or project management tool.

5. **Update Test Documentation:**

   - Update the test documentation with the manual test results and any new test scenarios or changes discovered during testing.




