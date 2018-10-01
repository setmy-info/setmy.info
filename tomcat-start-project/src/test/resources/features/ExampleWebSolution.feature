Feature: Example Web Solution

    Scenario: login page
        Given new opened web connection to base url
        When login.jsp page is opened
        Then title should be Login Page

    Scenario: servlet page without login
        Given new opened web connection to base url
        When TestServlet page is opened
        Then content h1Element on page should be Servlet TestServlet at /tomcat-start-project
        Then title should be Example Servlet

    Scenario: protected course list page without login
        Given new opened web connection to base url
        When private/listCourse page is opened
        Then title should be Login Page

    Scenario: protected course list page without login to login
        Given new opened web connection to base url
        When private/listCourse page is opened
        When loged in with user login and password g6p8
        Then title should be Courses list

    Scenario: protected greeting page without login
        Given new opened web connection to base url
        When private/viewGreeting page is opened
        Then title should be Login Page

    Scenario: protected greeting page without login to login
        Given new opened web connection to base url
        When private/viewGreeting page is opened
        When loged in with user login and password g6p8
        Then title should be Message from Groovy

    Scenario: protected private rest resource without login goes to login
        Given new opened web connection to base url
        When rest/private/example/person/first/Imre/last/Tabur page is opened
        Then title should be Login Page
