package com.university.synergy.elasticsearchsynergy.service;

import org.springframework.data.elasticsearch.core.query.Query;

public interface GenerateCriteriaService {

    /**
     * This function generate criteria using param
     *
     * @param userId criteria for search
     */
    Query generateCriteriaQuery(String userId);

}
