package com.pozhenskii.Lab2.task2;

import java.util.Random;

public class GaussRandom extends Random {
    Random random;

    public GaussRandom() {
        this.random = new Random();
    }

    public GaussRandom(int seed) {
        this.random = new Random(seed);
    }

    public double nextNormal(double mean, double sigma) {
        double u1 = random.nextDouble();
        double u2 = random.nextDouble();

        double z0 = Math.sqrt(-2.0 * Math.log(u1)) * Math.cos(2.0 * Math.PI * u2);
        return mean + sigma * z0;
    }

    public double nextNormal() {
        return nextNormal(0, 1);
    }
}
