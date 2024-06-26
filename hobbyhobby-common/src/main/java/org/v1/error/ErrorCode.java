package org.v1.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Common
    INTERNAL_SERVER_ERROR("COMMON_001", "Internal Server Error"),
    // User
    USER_NOT_FOUND("USER_001", "회원을 찾을 수 없습니다."),
    USER_EMAIL_NOT_FOUND("USER_002", "저장된 이메일이 없습니다."),
    USER_EMAIL_DUPLICATED("USER_003", "이메일이 중복 되었습니다."),
    USER_EMAIL_SEND_FAIL("USER_004", " 이메일 전송이 실패되었습니다."),
    USER_LOGIN_PASSWORD_FAIL("USER_005", "로그인 비밀번호가 잘못 되었습니다."),
    USER_NICKNAME_DUPLICATED("USER_006", "닉네임이 중복 되었습니다."),
    USER_CREATE_FAILED("USER_007", "사용자 저장을 실패 하였습니다."),
    USER_TYPE_IS_NOT_VALIDATE("USER_008", "유저 타입이 틀렸습니다."),
    TEXT_ARTICLE_NOT_FOUND("TEXT_ARTICLE_001", "H-Board 게시물을 찾을 수 없습니다."),
    TEXT_ALREADY_LIKE("TEXT_ARTICLE_002", "H-Board 게시물 좋아요를 이미 눌렀어요"),
    TEXT_ALREADY_DISLIKE("TEXT_ARTICLE_003", "H-Board 게시물 좋아요를 이미 취소했어요"),
    REVIEW_ARTICLE_NOT_FOUND("REVIEW_ARTICLE_001", "장비 리뷰 게시물을 찾을 수 없습니다."),
    REVIEW_COMMENT_NOT_FOUND("REVIEW_ARTICLE_002", "장비 리뷰 댓글을 찾을 수 없습니다."),
    PHOTO_ALREADY_LIKE("REVIEW_ARTICLE_003","장비 리뷰 게시물 좋아요를 이미 눌렀어요"),
    PHOTO_ALREADY_DISLIKE("REVIEW_ARTICLE_004","장비 리뷰 게시물 좋아요를 이미 취소했어요"),
    ARTICLE_ALREADY_LIKE("ARTICLE_001","게시물 좋아요를 이미 눌렀어요"),
    ARTICLE_ALREADY_DISLIKE("ARTICLE_002","게시물 좋아요를 이미 취소했어요"),
    GATHERING_CANNOT_APPEND("GATHERING_001","모임에 추가 할수 없어요"),
    GATHERING_ALREADY_JOINED("GATHERING_002","모임에 이미 가입했어요"),
    GATHERING_ALREADY_LEAVED("GATHERING_003","모임에 이미 탈퇴했어요"),
    COMMUNITY_ALREADY_LIKED("COMMUNITY_001", "이미 즐겨찾기한 커뮤니티입니다.");





    private static final Map<String, ErrorCode> ERROR_CODE_MAP=  Collections.unmodifiableMap(Stream.of(values())
            .collect(Collectors.toMap(ErrorCode::getMessage, Function.identity())));
    private final String code;
    private final String message;

    public static ErrorCode from(final String message) {
        if (ERROR_CODE_MAP.containsKey(message)) {
            return ERROR_CODE_MAP.get(message);
        }

        return ErrorCode.INTERNAL_SERVER_ERROR;
    }
}
