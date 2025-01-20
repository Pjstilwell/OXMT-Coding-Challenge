package com.peters.oxmtcodingchallenge;

import java.util.List;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import com.peters.Constants;

import java.util.ArrayList;

public class OutlierComputer {

    StatUtils statUtils;

    /**
     * Take in a list of machines and return a list of machines containing
     * only the machines that are deemed as outliers in the data
     * 
     * @param machineInput list of input machines
     * @return machineOutliers list of machines with outlying ages
     */
    public List<Machine> computeOutliers(List<Machine> machineInput) {
        List<Machine> machineOutliers = new ArrayList<Machine>();
        DescriptiveStatistics ageInDaysData = new DescriptiveStatistics();

        // Compile ageInDays of all machines
        for (Machine machine : machineInput) {
            ageInDaysData.addValue(machine.getAgeInDays());
        }

        // Compute ZScore for each machine
        for (Machine machine : machineInput) {

            // Add machine to outliers if found as outlier
            if (computeZScore(machine.getAgeInDays(), ageInDaysData) >= Constants.OUTLIER_THRESHOLD) {
                machineOutliers.add(machine);
            }
        }

        return machineOutliers;
    }

    /**
     * Compute the zscore of an input point and the set of data it belongs to
     * 
     * @param inputScore input datapoint to compute zscore of
     * @param stats      set of data
     * @return zscore
     */
    public double computeZScore(double inputScore, DescriptiveStatistics stats) {
        double mean = stats.getMean();
        double std = stats.getStandardDeviation();

        // return calculated zscore
        return (inputScore - mean) / std;
    }

}
