package org.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.v1.dto.CommunityListResponse;
import org.v1.model.Community;
import org.v1.model.CommunityStatusInfo;
import org.v1.model.Contents;
import org.v1.model.PhotoArticle;
import org.v1.service.CommunityService;
import response.HttpResponse;

import java.util.List;

@Tag(name = "Community", description = "유저 커뮤니티 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/community")
public class CommunityController {
    private final CommunityService communityService;
    @GetMapping("/popular")
    @Operation(summary = "인기 커뮤니티 정렬해서 가져오기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<List<CommunityListResponse>> getPopularCommunity(
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        List<CommunityStatusInfo> communityList = communityService.getPopularCommunity(userId);
        return HttpResponse.success(CommunityListResponse.ofUserCommunityList(communityList));
    }
    @GetMapping("/user")
    @Operation(summary = "유저 즐겨찾기 커뮤니티 가져오기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<List<CommunityListResponse>> getUserCommunity(
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        List<CommunityStatusInfo> communityList = communityService.getUserCommunity(userId);
        return HttpResponse.success(CommunityListResponse.ofUserCommunityList(communityList));
    }
    @GetMapping("/popular/contents")
    @Operation(summary = "인기 모임/인기 H-Log 40개 가져오기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Contents> getPopularCommunityPhotoContent(
    ) {
        Contents contents = communityService.getPopularContent();
        return HttpResponse.success(contents);
    }
}
