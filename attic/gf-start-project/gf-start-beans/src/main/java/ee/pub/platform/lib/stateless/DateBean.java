package ee.pub.platform.lib.stateless;

import java.util.Calendar;
//import javax.ejb.Stateless;

//@Stateless
public class DateBean implements Date {


    @Override
    public String today() {
        StringBuilder date = new StringBuilder();
        Calendar calendar = Calendar.getInstance();

        date.append(calendar.get(Calendar.MONTH)+1);
	date.append("/");
        date.append(calendar.get(Calendar.DAY_OF_MONTH));
	date.append("/");
        date.append(calendar.get(Calendar.YEAR));

        return date.toString();
    }

}
