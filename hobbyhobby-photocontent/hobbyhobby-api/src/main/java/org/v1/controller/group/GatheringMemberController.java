package org.v1.controller.group;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.v1.dto.request.GatheringMemberRequest;
import org.v1.service.group.GatheringMemberService;
import response.HttpResponse;

@Tag(name = "모임", description = "모임 API")
@RestController
@AllArgsConstructor
@RequestMapping("/api/gathering")
public class GatheringMemberController {
    private final GatheringMemberService memberService;

    @PostMapping("/single/member")
    @Operation(summary = "단일 모임 맴버 추가하기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> joinSingleGathering(
            @RequestBody GatheringMemberRequest request,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        memberService.joinGathering(request.toSingleGatheringMember(userId));
        return HttpResponse.successOnly();
    }

    @DeleteMapping("/single/member")
    @Operation(summary = "단일 모임 맴버 삭제하기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> leaveSingleGathering(
            @RequestBody GatheringMemberRequest request,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        memberService.leaveGathering(request.toSingleGatheringMember(userId));
        return HttpResponse.successOnly();
    }

    @PostMapping("/multi/member")
    @Operation(summary = "연합 모임 맴버 추가하기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> joinUnionGathering(
            @RequestBody GatheringMemberRequest request,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        memberService.joinGathering(request.toUnionGatheringMember(userId));
        return HttpResponse.successOnly();
    }

    @DeleteMapping("/multi/member")
    @Operation(summary = "연합 모임 맴버 삭제하기")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> leaveUnionGathering(
            @RequestBody GatheringMemberRequest request,
            @Parameter(hidden = true) @Valid @RequestHeader Long userId
    ) {
        memberService.leaveGathering(request.toUnionGatheringMember(userId));
        return HttpResponse.successOnly();
    }
}
