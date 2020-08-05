import org.junit.Test;

public class Main {
    @Test
    public void test1() {
        String nextDrain;
        Object l = MSK.selectTitle("6174").get(0);
        if (l.equals("5911"))
            nextDrain = "5912";
        else
            nextDrain = "5911";

        System.out.println(MSK.selectCustomerId("622000059774"));
        System.out.println(MSK.selectTitle("6174"));
        MSK.updatePropensityDrain("6174", nextDrain);
        System.out.println(MSK.selectTitle("6174"));
    }
}
