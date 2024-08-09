package ee.pub.platform.lib.stateful;


import javax.ejb.EJB;

import ee.pub.platform.lib.stateless.Date;


//@Stateful
public class LotteryBean implements Lottery {

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public String getDate() {
        return date.today();
    }

    @Override
    public void select(int number) {
        if( (number > -1) && (number < 10) ) {
            this.number = this.number + SPACE + 
                java.lang.Integer.toString(number);
	}
    }

    @Override
    public void setName(String name) {
 	this.name = name;
    }


    //Dependency injection to get an instance of the Date EJB.
    @EJB(name="Date") 
    private Date date;

    private String name = "Super Lotto";
    private String number = "";
    private static final String SPACE = " "; 
}
