package org.v1.controller.gatheringarticle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import response.HttpResponse;

import java.util.List;

@Tag(name = "모임", description = "모임 API")
@RestController
@AllArgsConstructor
@RequestMapping("/api/gathering")
public class GatheringArticleController {
    @GetMapping("/single")
    @Operation(summary = "단일 모임 게시글 최신순 제목 가져오기 max 10개")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> getSingleGatheringLatest(
    ) {
        return HttpResponse.successOnly();
    }
    @GetMapping("/multi")
    @Operation(summary = "연합 모임 게시글 최신순 제목 가져오기 max 10개")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> getMultiGatheringLatest(
    ) {
        return HttpResponse.successOnly();
    }
    @GetMapping("/single/search")
    @Operation(summary = "단일 모임 게시글 검색 제목 가져오기 max 10개")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> getSingleGatheringSearch(
    ) {
        return HttpResponse.successOnly();
    }
    @GetMapping("/multi/search")
    @Operation(summary = "연합 모임 게시글 최신순 검색 가져오기 max 10개")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> getMultiGatheringSearch(
    ) {
        return HttpResponse.successOnly();
    }
}
