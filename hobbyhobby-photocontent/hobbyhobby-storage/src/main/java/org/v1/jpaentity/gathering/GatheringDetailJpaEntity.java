package org.v1.jpaentity.gathering;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "gathering_detail", schema = "hobby_imageServer")
public class GatheringDetailJpaEntity {
    @Id
    @Column(name = "gathering_detail_id", nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "gathering_id", nullable = false)
    private GatheringJpaEntity gathering;
    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "link", nullable = false, length = 256)
    private String link;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "time", nullable = false)
    private LocalDateTime gatheringTime;

    @ColumnDefault("0")
    @Column(name = "likes", nullable = false)
    private Integer likes;
}