package com.university.synergy.elasticsearchsynergy.data.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDoc implements Serializable {

    @Id
    private String id;

    @Field
    private String fullName;

    @Field
    private Map<String, Object> data;

}
