package info.setmy.services.text.linguistics;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ProbesTest {

    @Test
    public void test1() {
        List<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        list1.add("C");

        List<String> list2 = new ArrayList<>();
        list2.add("B");
        list2.add("C");
        list2.add("D");

        List<String> common = new ArrayList<>(list1);
        common.retainAll(list2);

        //Commpn
        System.out.println(common);
    }
}
