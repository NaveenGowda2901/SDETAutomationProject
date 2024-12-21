# Selenium Web Automation Project(with Java)

 - Author: Sana Anwar Farooqui
 - Framework Type: Selenium-TestNG Hybrid framework
 - Tools and techniques used: Java, Maven, Selenium, TestNG, Page Object Model
 - Functionalities Automated: User Account Registration, User sigin, Account Deletion, Search Product and Cart
 - Properties file to get app URL, execution environment, username and email
 - Data Driven testing for Register and Sigin functionalities using Excel Sheet as data supplier
 - Log42j for logging with logging level info, debug and Warn
 - Reports: TestNG reports and Extent reports
 - Screenshot upon test failure using ITestListener interface
 - Retry executing failed test cases for three times using 'IRetryAnalyzer' and 'IAnnotationTransformer' interface
 - Base class with Before and After Methods for setUp and tearDown.
 - setUp method to run the test cases localy and remote via Selenium Grid and Docker(docker-compose.yml)
 - Continous Integration to Jenkins and execute tests via Jenkins

