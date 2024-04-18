package org.v1.email;

import org.v1.domain.user.domain.User;

public record Mail(
        String address,
        String title,
        String message
) {
    public static Mail from(final User user, final String randomPassword) {
        return new Mail(
                user.getEmail(),
                user.getNickname()+"님의 하비하비 임시비밀번호 안내 이메일 입니다.",
                "안녕하세요. 하비하비 임시비밀번호 안내 관련 이메일 입니다." + "임시 비밀번호는 "
                        + randomPassword + " 입니다."
        );
    }
}
