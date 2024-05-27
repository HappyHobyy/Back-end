package org.v1.jpaentity.rental;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.v1.jpaentity.rental.RentalReceiptJpaEntity;

@Getter
@Setter
@Entity
@Table(name = "rental_address", schema = "hobby_imageServer")
public class RentalAddressJpeEntity {
    @Id
    @Column(name = "rental_address_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "receipt_id", nullable = false)
    private RentalReceiptJpaEntity receipt;

    @Column(name = "postal_code", nullable = false, length = 30)
    private String postalCode;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "town", nullable = false, length = 100)
    private String town;

    @Column(name = "street_address", nullable = false, length = 100)
    private String streetAddress;

    @Column(name = "specific_address", nullable = false, length = 100)
    private String specificAddress;

}