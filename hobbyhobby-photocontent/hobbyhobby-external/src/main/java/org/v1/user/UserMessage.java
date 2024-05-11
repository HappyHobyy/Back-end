package org.v1.user;

import org.v1.model.User;

public record UserMessage(
        Long userId,
        String userNickName,
        String imageUrl
) {
    public record CreateUserMessage(
            Long userId,
            String userNickName
    ){
        public  User toUser() {
            return User.withoutUrl(this.userId,this.userNickName);
        }
    }
    public record UpdateUserMessage(
            Long userId,
            String userNickName,
            String imageUrl
    ){
        public  User toUser() {
            return User.withId(this.userId,this.userNickName,this.imageUrl);
        }
    }
    public record DeleteUserMessage(
            Long userId
    ){ }
}
