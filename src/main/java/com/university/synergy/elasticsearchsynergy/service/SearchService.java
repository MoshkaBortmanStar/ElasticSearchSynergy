package com.university.synergy.elasticsearchsynergy.service;

import com.university.synergy.elasticsearchsynergy.data.dto.ResponseObject;
import reactor.core.publisher.Flux;

public interface SearchService {

    /**
     * This function go to elastic and find all user documents
     *
     * @param userId criteria for search
     */
    Flux<ResponseObject> searchUserDoc(String userId);
}
