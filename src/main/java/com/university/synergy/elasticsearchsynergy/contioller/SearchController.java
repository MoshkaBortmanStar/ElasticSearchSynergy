package com.university.synergy.elasticsearchsynergy.contioller;

import com.university.synergy.elasticsearchsynergy.data.dto.ResponseObject;
import com.university.synergy.elasticsearchsynergy.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("synergy")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/user/docs/{id}")
    public Flux<ResponseObject> getUserDoc(@PathVariable String userId) {
        return searchService.searchUserDoc(userId);
    }

}
