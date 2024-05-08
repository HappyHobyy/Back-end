package org.v1.rabbitmq;

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
        public static CreateUserMessage of(User user) {
            return new CreateUserMessage(user.getId().value(), user.getNickname());
        }
    }
    public record UpdateUserMessage(
            Long userId,
            String userNickName,
            String imageUrl
    ){
        public static UpdateUserMessage of(User user) {
            return new UpdateUserMessage(user.getId().value(), user.getNickname(), user.getImageUrl());
        }
    }
    public record DeleteUserMessage(
            Long userId
    ){
        public static DeleteUserMessage of(Long userId) {
            return new DeleteUserMessage(userId);
        }
    }
}
