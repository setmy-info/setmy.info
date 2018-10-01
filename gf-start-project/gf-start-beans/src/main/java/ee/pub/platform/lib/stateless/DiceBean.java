package ee.pub.platform.lib.stateless;

import java.util.Random;
//import javax.ejb.Stateless;

//@Stateless
public class DiceBean implements Dice {

    @Override
    public int play() {
	Random random = new Random();
	return random.nextInt(10);
    }

}
