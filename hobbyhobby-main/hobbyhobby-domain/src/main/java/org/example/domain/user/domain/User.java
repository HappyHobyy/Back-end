package org.example.domain.user.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.domain.BaseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "user")
public class User  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "type")
    private String type;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    public void hashedPassword(final String password) {
        this.password = password;
    }
}
