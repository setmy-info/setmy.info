package ee.pub.web;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Groovy removed - gives to much unstability for build (switching from 1.7 to
 * 1.8 java).
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Greeting extends ActionSupport {

    private String greeting = "Hello from Groovy!";

    @Override
    public String execute() {
        System.out.println("Grooooovy!");
        return "SUCCESS";
    }

    /**
     * http://localhost:8080/tomcat-start-project/private/viewGreeting
     *
     */
    public String view() {
        System.out.println("Grooooovy!");
        return "SUCCESS";
    }

    public boolean validateView() {
        return true;
    }

    public boolean validateDoView() {
        return true;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
