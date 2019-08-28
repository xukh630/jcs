package com.jcs.opendota;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculateTest {

    @Test
    public void sqrt(){

        BigDecimal two = new BigDecimal(2).setScale(17,BigDecimal.ROUND_HALF_UP);
        BigDecimal target = new BigDecimal(4).setScale(17,BigDecimal.ROUND_HALF_UP);;
        BigDecimal accuracy = new BigDecimal(0.0000000000000001);

        BigDecimal min = target;

        while(min.multiply(min).compareTo(target) == 1){
            min = min.divide(two);
        }

        BigDecimal max = min.multiply(two);
        BigDecimal result = max;

        while (result.multiply(result).subtract(target).abs().compareTo(accuracy) == 1) {

            if (result.multiply(result).compareTo(target) ==1){
                max = result;
            } else {
                min = result;
            }
            result = max.add(min).divide(two);
        }
        System.out.println(result);
    }

    //3.16227766016837934237582175001080031506717205047607421875
    //3.1622776598669588565826416015625
    //3.1622776601683795
    @Test
    public void test(){
        System.out.println(Math.sqrt(10));
        BigDecimal x = new BigDecimal("3.1622776599");
        BigDecimal result = x.multiply(x).setScale(2,BigDecimal.ROUND_HALF_UP);
        System.out.println(result);
    }
}
