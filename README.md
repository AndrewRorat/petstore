# API test automation framework for Petstore

## Prerequisites

- Java 17
- Maven 4+

## How to run tests
1. Clone the repository
2. Navigate to the project directory
3. Run the following command to execute the tests:
   - mvn clean test -Dsuite=<suite>.xml -Denvironment=<environment> ## use dev for this execution  

4. Test reports will be generated in the `target/allure-results` directory.
5. To generate an Allure report, run the following command after test execution:
   - mvn allure:report

6. To view the Allure report, run:
   - mvn allure:serve
7. To run tests directly in IntelliJ:
   - Open run.properties file and set the desired environment (dev or stg)
   - Right-click on the desired test class or method and select "Run"

## Project Structure
- `src/main/java/business`: Contains the main business codebase including API clients, models, enums, and utilities
- `src/main/java/core`: Contains Constants, Configurations
- `src/main/resources`: Contains properties files for different environments and base URLs
- `src/test/java/tests`: Contains test classes organized by feature
- `src/test/resources`: Contains test data and tstNG suite files
- `pom.xml`: Maven configuration file
- `logback-test.xml`: Logback configuration file for logging 
  - (e.x., 13:02:02.617 INFO  [main] business.clients.pet.PetClient - Uploading image for pet with ID: 13777900)

## Testing Approach

- OOP instead of static

- Domain clients (StoreClient, PetClient, UserClient) are implemented as class instances that receive a RequestSpecification from config.

- Test data factories (OrderFactory) generate both valid and invalid objects.

- Data-driven testing
  - Some tests (for status) are implemented with DataProviders to cover boundary values using one code block.