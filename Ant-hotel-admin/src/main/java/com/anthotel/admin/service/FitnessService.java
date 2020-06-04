package com.anthotel.admin.service;

import com.anthotel.admin.dto.Fitness;
import com.anthotel.admin.dto.UserSearch;

import java.util.List;

public interface FitnessService {
    List<Fitness> fetchFitnessList();

    List<Fitness> searchFitness(UserSearch userSearch);
}
