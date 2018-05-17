package com.sum;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main(String[] args) {
//        sum(5, 2);

        sum_v2(5, 2);
    }

    static void sum(int n, int c) {
        BigInteger productElements = BigInteger.ONE;
        BigInteger sum = BigInteger.ZERO;
        long firstProductElement = 1;
        //System.out.println(new Date());
        for (int i = 1; i <= c; i++) {
            System.out.println(i);
            productElements = productElements.multiply(BigInteger.valueOf(i));
            sum = sum.add(productElements);
        }
        System.out.println(sum);
        for (long i = ++c; i < n; i++) {
            productElements = productElements.divide(BigInteger.valueOf(firstProductElement))
                    .multiply(BigInteger.valueOf(i));
            sum = sum.add(productElements);
            firstProductElement += 1;
        }
        String sumStr = sum.toString();
        sum = sumStr.length() > 10 ? BigInteger.valueOf(Long.valueOf(sumStr.substring(0, 10))) : sum;
        //System.out.println(new Date());
        System.out.println(sum);

    }


    static void sum_v2(Integer n, Integer c) {

        List<Integer> collect = IntStream.rangeClosed(1, c).parallel().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        System.out.println(collect);

    }


}
