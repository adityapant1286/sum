package com.sum;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class ParallelMultiplier extends RecursiveTask<BigInteger> {

    private final BigInteger threshhold = BigInteger.valueOf(80);

    private final BigInteger upperLimit;
    private final BigInteger lowerLimit;

    public ParallelMultiplier(BigInteger lowerLimit, BigInteger upperLimit) {

        if (lowerLimit.compareTo(upperLimit) >= 0)
            throw new IllegalArgumentException(String.format("Lower limit >= upper limit (%d >= %d)", lowerLimit, upperLimit));

        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    @Override
    protected BigInteger compute() {

        if (upperLimit.subtract(lowerLimit).compareTo(threshhold) <= 0) {

        }

        return null;
    }

    private BigInteger multiply() {

        return IntStream.range(lowerLimit.intValue(), upperLimit.intValue())
                        .mapToObj(BigInteger::valueOf)
                        .reduce(BigInteger.ONE, BigInteger::multiply);

//        BigInteger product = ONE;
//        for (BigInteger i = lowerLimit; i.compareTo(upperLimit) < 0; i = i.add(ONE))
//            product = product.multiply(i);
//        return product;
    }

    private BigInteger multiplyParallel() {

        BigInteger half = upperLimit.add(lowerLimit).shiftRight(1);

        final ParallelMultiplier multiplier1 = new ParallelMultiplier(lowerLimit, half);
        final ParallelMultiplier multiplier2 = new ParallelMultiplier(half, upperLimit);

        multiplier1.fork();

        return multiplier2.compute().multiply(multiplier1.join());
    }
}
