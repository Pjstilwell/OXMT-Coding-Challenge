package com.peters.oxmtcodingchallenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.peters.oxmtcodingchallenge.exceptions.BadAgeException;
import com.peters.oxmtcodingchallenge.exceptions.BadTimeUnitException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MachineController {

    Logger logger = LoggerFactory.getLogger(MachineController.class);

    OutlierComputer computer = new OutlierComputer();

    /**
     * Takes in a list of machines with their ages and returns the machines with
     * potential
     * erroneous age inputs
     * 
     * @param machineInput a list of objects in the form {machineID: x, age: "y
     *                     <time unit>"}
     * @return the list of outliers in the data
     */
    @PostMapping("/findOutliers")
    List<Machine> findOutliers(@RequestBody List<Machine> machineInput) {

        logger.info("Computing Machine age outliers...");

        List<Machine> machineOutliers = computer.computeOutliers(machineInput);

        logger.info("Finished computing Machine age outliers with status " + HttpStatus.OK);

        return machineOutliers;
    }

    /**
     * Handles request passing in bad/unhandled time unit
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(BadTimeUnitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleBadTimeUnitException(Exception e) {
        return e.getMessage();
    }

    /**
     * Handles request passing in bad/unhandled time unit
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(BadAgeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleBadAgeException(Exception e) {
        return e.getMessage();
    }

}
