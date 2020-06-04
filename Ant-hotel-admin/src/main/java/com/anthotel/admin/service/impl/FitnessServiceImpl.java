package com.anthotel.admin.service.impl;

import com.anthotel.admin.dto.Fitness;
import com.anthotel.admin.dto.UserSearch;
import com.anthotel.admin.mapper.FitnessMapper;
import com.anthotel.admin.service.FitnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FitnessServiceImpl implements FitnessService {
    @Autowired
    FitnessMapper fitnessMapper;
    @Override
    public List<Fitness> fetchFitnessList() {
        return fitnessMapper.fetchFitnessList();
    }

    @Override
    public List<Fitness> searchFitness(UserSearch userSearch) {
        return fitnessMapper.searchFitness(userSearch);
    }
}
