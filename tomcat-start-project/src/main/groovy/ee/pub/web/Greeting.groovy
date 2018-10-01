package ee.pub.web

import com.opensymphony.xwork2.ActionSupport

class Greeting2 extends ActionSupport {

    final static int serialVersionUID = 1234234234;

    String greeting = "Hello from Groovy!";

    public String execute(){
        println "Grooooovy!"
        return ActionSupport.SUCCESS;
    }	

    /**
     * http://localhost:8080/tomcat-start-project/private/viewGreeting
     **/
    public String view(){
        println "Grooooovy!"
        return  ActionSupport.SUCCESS;
    }
     
    public validateView() {
        return true
    }
    
    public validateDoView() {
        return true
    }
}

