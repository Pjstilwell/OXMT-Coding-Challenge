package com.peters.oxmtcodingchallenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.peters.Constants;
import com.peters.oxmtcodingchallenge.exceptions.BadAgeException;
import com.peters.oxmtcodingchallenge.exceptions.BadTimeUnitException;

@JsonIgnoreProperties({ "ageInDays" })
public class Machine {

    Logger logger = LoggerFactory.getLogger(Machine.class);

    private long machineID;
    private String age;

    private String ageTimeUnit;
    private long ageNumber;
    private double ageInDays;

    @JsonCreator
    Machine(@JsonProperty("machineID") long machineID, @JsonProperty("age") String age) {
        this.machineID = machineID;
        this.age = age;

        String[] splitAge;

        // Compute age in days
        try {
            splitAge = age.split(" ");
            this.ageNumber = Long.parseLong(splitAge[0]);
            this.ageTimeUnit = splitAge[1];
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            logger.error("BadAgeException thrown with machineID: " + this.machineID + " and age: " + this.age);
            throw new BadAgeException(machineID, age);
        }

        computeAgeInDays();
    }

    public long getMachineID() {
        return this.machineID;
    }

    public void setMachineID(long machineID) {
        this.machineID = machineID;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public double getAgeInDays() {
        return this.ageInDays;
    }

    public void setAgeInDays(double ageInDays) {
        this.ageInDays = ageInDays;
    }

    /**
     * Computes the age in days of a machine based on the
     * time unit input
     * 
     * @throws BadTimeUnitException
     */
    private void computeAgeInDays() {
        // using approx averages for days in each time unit
        switch (this.ageTimeUnit) {
            case "years":
            case "year":
                this.ageInDays = this.ageNumber * Constants.DAYS_IN_YEAR;
                break;
            case "months":
            case "month":
                this.ageInDays = this.ageNumber * Constants.DAYS_IN_MONTH;
                break;
            case "weeks":
            case "week":
                this.ageInDays = this.ageNumber * Constants.DAYS_IN_WEEK;
                break;
            case "days":
            case "day":
                this.ageInDays = this.ageNumber;
                break;
            default:
                logger.error("BadTimeUnitException thrown with machineID: " + this.machineID + " and ageTimeUnit: "
                        + this.ageTimeUnit);
                throw new BadTimeUnitException(this.machineID, this.ageTimeUnit);
        }
    }
}