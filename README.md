## Dependencies
- Java
- Maven
- Selenium WebDriver
- TestNG
- SLF4J
- Logback
- Lombok
- Allure
- WebDriverManager
- Owner (Aeonbits)

## Preparation before run
1. For select browser for tests running, properties file {project_folder}/src/main/resources/application.properties needs to be changed:
- for running on Mozilla: ```webdriver.browser.name=firefox```
- for running on Chrome: ```webdriver.browser.name=chrome```

## How to run
1. Open a terminal or command line
2. Remove previous allure-results (it they exist) to prevent results mixing (optional)
   ```WIN$: rmdir /S /Q allure-results```
   ```UNIX$: rm -rf allure-results```
3. Execute the command to compile the project (mandatory only the first time launch and after code changes):
   ```mvn clean install -Dmaven.test.skip=true```
4. Execute the command to run tests:
   ```mvn test -Dstart.url={there is a site URL}```
5. Execute the command to generate Allure report:
   ```mvn allure:serve```

## Properties in application.properties
- *start.url* - url of application under test
- *webdriver.browser.name* - browser name which will be used for test run
- *timeouts.page* - timeout for pages loading
- *timeouts.element* - default timeout for loading elements
- *timeouts.minimal* - minimal timeout for loading elements (use if default timeout for loading elements is too long)