package com.anthotel.admin.mapper;

import com.anthotel.admin.dto.Fitness;
import com.anthotel.admin.dto.UserSearch;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FitnessMapper {
    List<Fitness> fetchFitnessList();

    List<Fitness> searchFitness(UserSearch userSearch);

}
