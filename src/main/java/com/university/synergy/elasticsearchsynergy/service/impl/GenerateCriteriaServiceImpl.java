package com.university.synergy.elasticsearchsynergy.service.impl;

import com.university.synergy.elasticsearchsynergy.service.GenerateCriteriaService;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class GenerateCriteriaServiceImpl implements GenerateCriteriaService {

    private static final String TABLE_NAME = "table_name";
    private static final String USER_ID = "user_id";

    public Query generateCriteriaQuery(String userId) {
        var mainCriteria = new Criteria(TABLE_NAME).is("user_doc");
        var subCriteria = generateSubCriteria(userId);

        mainCriteria.subCriteria(subCriteria);
        return new CriteriaQuery(mainCriteria);
    }

    private Criteria generateSubCriteria(String userId) {
        return new Criteria(USER_ID).is(userId);
    }
}
