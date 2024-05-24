package org.v1.jpaentity.photo;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import org.v1.jpaentity.community.CommunityJpaEntity;
import org.v1.jpaentity.user.UserJpaEntity;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "photo_content")
public class PhotoArticleJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_content_id", nullable = false)
    private Long id;

    @Column(name = "content")
    private String content;

    @ColumnDefault("0")
    @Column(name = "comments", nullable = false)
    private Integer comments;

    @ColumnDefault("0")
    @Column(name = "likes", nullable = false)
    private Integer likes;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", columnDefinition = "timestamp")
    private LocalDateTime modifiedAt;


    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false) // foreign key (userId) references User (id)
    private UserJpaEntity user;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "community_id", nullable = false) // foreign key (userId) references Community (id)
    private CommunityJpaEntity community;

    public static PhotoArticleJpaEntity onlyWithId(Long id) {
        return PhotoArticleJpaEntity.builder()
                .id(id)
                .build();
    }
}