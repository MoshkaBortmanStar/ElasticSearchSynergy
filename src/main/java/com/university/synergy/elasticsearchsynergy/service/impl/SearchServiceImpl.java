package com.university.synergy.elasticsearchsynergy.service.impl;

import com.university.synergy.elasticsearchsynergy.data.document.UserDoc;
import com.university.synergy.elasticsearchsynergy.data.dto.ResponseObject;
import com.university.synergy.elasticsearchsynergy.service.GenerateCriteriaService;
import com.university.synergy.elasticsearchsynergy.service.ResponseObjectMapper;
import com.university.synergy.elasticsearchsynergy.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    @Value("${spring.data.elasticsearch.index}")
    private String index;

    private final ResponseObjectMapper responseObjectMapper;
    private final GenerateCriteriaService generateCriteriaService;
    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    @Override
    public Flux<ResponseObject> searchUserDoc(String userId) {
        var criteriaQuery = generateCriteriaService.generateCriteriaQuery(userId);
        return reactiveElasticsearchTemplate.search(criteriaQuery, UserDoc.class, IndexCoordinates.of(index))
                .map(responseObjectMapper::toResponseObject);
    }
}
