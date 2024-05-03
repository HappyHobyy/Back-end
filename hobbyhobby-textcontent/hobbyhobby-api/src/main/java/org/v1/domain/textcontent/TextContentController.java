package org.v1.domain.textcontent;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.v1.textcontent.TextContent;
import org.v1.textcontent.TextContentService;
import response.HttpResponse;

import java.util.List;

@Tag(name = "H-Board", description = "H-Board API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/textcontent")
public class TextContentController {
    private final TextContentService textContentService;
    @DeleteMapping("/get")
    @Operation(summary = "h-board 게시글 가져오기 max 10개")
    @Parameter(name = "Authorization", description = "Access token", required = true, in = ParameterIn.HEADER)
    public HttpResponse<Object> getTextContent(
            @RequestBody Long categoryId
    ) {
        List<TextContent> textContentList = textContentService.getTenTextContent(categoryId);
        return HttpResponse.success(textContentList);
    }
}
