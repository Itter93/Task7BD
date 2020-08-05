import org.junit.Test;

public class Main {
    @Test
    public void test1(){
        System.out.println(MSK.selectCustomerId("622000059774"));
        System.out.println(MSK.selectTitle("6174"));
        MSK.updatePropensityDrain(6174);
        System.out.println(MSK.selectTitle("6174"));


    }
}
