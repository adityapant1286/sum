package com.sum;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class App {

    public static void main(String[] args) {

        final App app = new App();

        app.sumOfConsecutive(5, 2, 10); // Sum = 21 | TimeTaken = 74 millis
        app.sumOfConsecutive(10000000, 200, 10); // Sum = 4965134354 | TimeTaken = 30689 millis (10 digits limit)
        app.sumOfConsecutive(1000000, 200, 10); // Sum = 4876116127 | TimeTaken = 2619 millis (10 digits limit)
    }

    public void sumOfConsecutive(Integer n, Integer c, int noOfDigits) {

        boolean nIsGreaterThanC = true;

        if (n.compareTo(c) < 0) { // if n is less than c, then use available numbers
            c = n;
            nIsGreaterThanC = false;
        }

        final Calc calc = new Calc();
        long start = System.currentTimeMillis();
        // 1...c
        IntStream.rangeClosed(1, c).mapToObj(BigInteger::valueOf).forEach(calc::productSum);

        if (nIsGreaterThanC)
            // ++c...n
            LongStream.range(++c, n).mapToObj(BigInteger::valueOf).forEach(calc::divideProductSum);

        final String sum = adjustToLimit(calc.getSum().toString(), noOfDigits);

        displaySum(sum, start);
    }

    private String adjustToLimit(String sum, int limit) { return sum.length() > limit ? sum.substring(0, limit) : sum; }

    private void displaySum(String sum, long start) { System.out.println(String.format("Sum = %s | TimeTaken = %s", sum, timeTaken(start))); }

    private String timeTaken(long start) { return String.format("%d millis", (System.currentTimeMillis() - start)); }


    /**
     * <pre>
     * This class will be used to calculate multiplication
     * and sum
     * </pre>
     */
    private class Calc {

        private BigInteger product = BigInteger.ONE;
        private BigInteger sum = BigInteger.ZERO;
        private BigInteger holder = BigInteger.ONE;


        void productSum(BigInteger prod) {
            product(prod);
            add(product);
        }

        void divideProductSum(BigInteger bigInteger) {
            product = product.divide(holder).multiply(bigInteger);
            holder = holder.add(BigInteger.ONE);
            add(product);
        }

        BigInteger getSum() { return sum; }

        /**
         * <pre>
         * As per Java documentation {@link BigInteger#multiplyKaratsuba(BigInteger, BigInteger)}
         * when the result exceeds the {@link BigInteger#KARATSUBA_THRESHOLD}, the
         * Karatsuba algorithm executes which has {@code O(n^(log2(3))), or O(n^1.585)} complexity.
         *
         * If the result exceeds the {@link BigInteger#TOOM_COOK_THRESHOLD}, the
         * 3-way Toom-Cook algorithm executes which has {@code O(n^1.465)} complexity.
         * </pre>
         * @param prod - input
         */
        private void product(BigInteger prod) { product = product.multiply(prod); }

        private void add(BigInteger bigInteger) { sum = sum.add(bigInteger); }
    }
}
