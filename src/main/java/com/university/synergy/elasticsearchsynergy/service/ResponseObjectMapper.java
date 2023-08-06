package com.university.synergy.elasticsearchsynergy.service;

import com.university.synergy.elasticsearchsynergy.data.document.UserDoc;
import com.university.synergy.elasticsearchsynergy.data.dto.ResponseObject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.elasticsearch.core.SearchHit;

@Mapper(componentModel = "spring")
public interface ResponseObjectMapper {

    @Mapping(target = "data", source = "userDoc.data")
    @Mapping(target = "fullName", source = "userDoc.fullName")
    ResponseObject toResponseObject(SearchHit<UserDoc> userDoc);

}
