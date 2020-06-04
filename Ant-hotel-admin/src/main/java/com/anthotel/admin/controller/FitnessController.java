package com.anthotel.admin.controller;


import com.anthotel.admin.dto.Fitness;
import com.anthotel.admin.dto.UserSearch;
import com.anthotel.admin.service.FitnessService;
import com.anthotel.common.base.ResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("fitness")
public class FitnessController {
    @Autowired
    FitnessService fitnessService;
    @GetMapping("list")
    public ResultKit getFitnessList() {
        List<Fitness> fitnessList = fitnessService.fetchFitnessList();
        if (fitnessList != null) {
            return ResultKit.newSuccessResult(fitnessList);
        }
        return ResultKit.newFailedResult("error");
    }
    @GetMapping("search")
    public ResultKit searchFitness(@RequestParam(value = "userId",required = false) String userId,
                                   @RequestParam(value = "name",required = false) String name) {
        UserSearch userSearch = new UserSearch(userId, name);
        List<Fitness> fitnessList = fitnessService.searchFitness(userSearch);
        if (fitnessList != null) {
            return ResultKit.newSuccessResult(fitnessList);
        }
        return ResultKit.newFailedResult("error");
    }
}
