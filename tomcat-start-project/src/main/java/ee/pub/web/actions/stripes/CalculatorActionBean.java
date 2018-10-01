package ee.pub.web.actions.stripes;

import ee.pub.model.Person;
import ee.pub.service.CourseService;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.apache.log4j.Logger;

@UrlBinding("/pub/calc.action")
public class CalculatorActionBean implements ActionBean {

    private static final Logger LOG = Logger.getLogger(CalculatorActionBean.class);

    @SpringBean//("service")
    private CourseService service;

    private ActionBeanContext context;

    @Validate(required = true)
    private double numberOne;

    @Validate(required = true)

    private double numberTwo;

    private double result;

    private Person person;

    @DefaultHandler//http://localhost:8080/tomcat-start-project/pub/calc.action?numberOne=12&numberTwo=23
    public Resolution addition() {
        LOG.debug("Stripes action!!");
        this.setPerson(service.makePerson("Imre", "Tabur"));
        result = getNumberOne() + getNumberTwo();
        return new ForwardResolution("/WEB-INF/stripes/example.jsp");
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    public double getNumberOne() {
        return numberOne;
    }

    public void setNumberOne(double numberOne) {
        this.numberOne = numberOne;
    }

    public double getNumberTwo() {
        return numberTwo;
    }

    public void setNumberTwo(double numberTwo) {
        this.numberTwo = numberTwo;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @ValidationMethod(on = "division")
    public void avoidDivideByZero(final ValidationErrors errors) {
        if (this.numberTwo == 0) {
            errors.add("numberTwo", new SimpleError("Dividing by zero is not allowed."));
        }
    }

    public CourseService getService() {
        return service;
    }

    public void setService(CourseService service) {
        this.service = service;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @HandlesEvent("person")//http://localhost:8080/tomcat-start-project/pub/calc.action?person&numberOne=12&numberTwo=23
    public Resolution person() {
        numberOne = 1;
        numberTwo = 2;
        result = numberOne + numberTwo;
        this.setPerson(service.makePerson("Peeter", "Esimene"));
        return new ForwardResolution("/WEB-INF/stripes/example.jsp");
    }
}
