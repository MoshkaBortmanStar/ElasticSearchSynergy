package com.university.synergy.elasticsearchsynergy.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Map;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject {

    @Field
    private String fullName;

    @Field
    private Map<String, Object> data;

}
