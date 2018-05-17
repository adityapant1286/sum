package com.sum;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
//        add(5, 2);
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

        IntStream.rangeClosed(1, c).mapToObj(BigInteger::valueOf).forEach(Calc::productThenSum);
        LongStream.range(++c, n).mapToObj(BigInteger::valueOf).forEach(Calc::divideProductSum);


        String sum = Calc.getSum().toString();

        System.out.println(sum.length() > 10 ? sum.substring(0, 10) : sum);

    }


    private static class Calc {

        private static BigInteger product = BigInteger.ONE;
        private static BigInteger sum = BigInteger.ZERO;
        private static BigInteger holder = BigInteger.ONE;

        static void productThenSum(BigInteger prod) {
            product(prod);
            add(product);
        }

        static void divideProductSum(BigInteger bigInteger) {
            product = product.divide(holder).multiply(bigInteger);
            holder = holder.add(BigInteger.ONE);
            add(product);
        }

        static BigInteger getSum() { return sum; }

        private static void product(BigInteger prod) { product = product.multiply(prod); }

        private static void add(BigInteger bigInteger) { sum = sum.add(bigInteger); }
    }
}
