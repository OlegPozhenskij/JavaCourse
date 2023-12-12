package com.pozhenskii.task2;

import java.util.*;
import java.util.Random;

public class ArrayStatistics {
    private int[] nums;

    public int[] getOriginalArray() {
        return nums;
    }

    public ArrayStatistics(int[] nums) {
        this.nums = nums;
    }

    public int[] mode() {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        if (nums.length == 0) {
            return new int[]{};
        }

        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        int maxFrequency = Collections.max(frequencyMap.values());

        List<Integer> modes = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                modes.add(entry.getKey());
            }
        }

        int[] expectedMode = new int[modes.size()];
        for (int i = 0; i < modes.size(); i++) {
            expectedMode[i] = modes.get(i);
        }

        return expectedMode;
    }

    public double median() {
        Arrays.sort(nums);
        int length = nums.length;

        if (length == 0){
            return 0;
        } else if(length % 2 == 0) {
            return (nums[length / 2 - 1] + nums[length / 2]) / 2.0;
        } else {
            return nums[length / 2];
        }
    }

    public double average() {
        int sum = 0;
        if (nums.length != 0) {
            for (int num: nums) {
                sum += num;
            }
            return (double) sum / nums.length;
        } else {
            return 0;
        }
    }

    public double variance() {
        if (nums.length == 0) return 0.0;

        double avg = average();
        double sumSquaredDeviations = 0;
        double deviation = 0;

        for (int num : nums) {
            deviation = num - avg;
            sumSquaredDeviations += deviation * deviation;
        }

        if (sumSquaredDeviations == 0) return 0.0;

        return sumSquaredDeviations;
    }

    public double geomMean() {
        if (nums.length == 0) return 0;

        double product = nums[0];

        for (int i = 1; i < nums.length; i++) {
            product *= nums[i];
        }

        double geometricMean = Math.pow(product, 1.0 / nums.length);

        return geometricMean;
    }

    public int[] shuffle(int seed) {
        if (nums.length == 0) return new int[]{};

        var shuffledArr = Arrays.copyOf(nums, nums.length);
        var rand = new Random(seed);

        for (int i = shuffledArr.length - 1; i > 0; i--) {
            int rIndex = rand.nextInt(i + 1);

            int buff = shuffledArr[rIndex];
            shuffledArr[rIndex] = shuffledArr[i];
            shuffledArr[i] = buff;
        }

        return shuffledArr;
    }


    public int[] sample(int sampleSize) {

        if (nums.length == 0 || sampleSize <= 0) {
            return new int[]{};
        }
        Integer[] temp = Arrays.stream(nums).boxed().toArray(Integer[]::new);

        Collections.shuffle(Arrays.asList(temp));

        return Arrays.stream(temp)
                .limit(sampleSize)
                .mapToInt(Integer::intValue)
                .toArray();
    }

}
