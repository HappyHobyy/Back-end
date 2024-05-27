package org.v1.jpaentity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "union_gathering_detail", schema = "hobby_imageServer")
public class UnionGatheringDetailJpaEntity {
    @Id
    @Column(name = "union_gathering_detail_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "union_gathering_id", nullable = false)
    private UnionGatheringJpaEntity unionGathering;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "link", nullable = false, length = 256)
    private String link;

    @ColumnDefault("0")
    @Column(name = "likes", nullable = false)
    private Integer likes;

}