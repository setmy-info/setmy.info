package ee.pub.platform.lib.stateful;

//import javax.ejb.Remote;

//@Remote
public interface Lottery {

    public String getName();
    public String getNumber();
    public String getDate();

    public void select(int number);
    public void setName(String name);
}
