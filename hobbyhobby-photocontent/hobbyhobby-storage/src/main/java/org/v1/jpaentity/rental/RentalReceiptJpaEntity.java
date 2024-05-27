package org.v1.jpaentity.rental;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "rental_receipt", schema = "hobby_imageServer")
public class RentalReceiptJpaEntity {
    @Id
    @Column(name = "rental_reciept_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "reservation_id", nullable = false)
    private RentalReservationJpaEntity reservation;

    @Column(name = "receiver_name", nullable = false, length = 30)
    private String receiverName;

    @Column(name = "receiver_hp", nullable = false, length = 11)
    private String receiverHp;

    @Column(name = "receiver_email", nullable = false, length = 256)
    private String receiverEmail;

    @Column(name = "deliver_instructions", length = 256)
    private String deliverInstructions;

}