package org.v1.jpaentity.rental;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.v1.jpaentity.rental.RentalJpaEntity;
import org.v1.jpaentity.user.UserJpaEntity;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "rental_reservation", schema = "hobby_imageServer")
public class RentalReservationJpaEntity {
    @Id
    @Column(name = "reservation_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "rental_id", nullable = false)
    private RentalJpaEntity rental;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    @Column(name = "rent_at")
    private LocalDateTime rentAt;

    @Column(name = "return_at")
    private LocalDateTime returnAt;
}