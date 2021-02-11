package ru.sapteh.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(name = "user_id")
    @ManyToOne
    @NonNull
    private User user;
    @JoinColumn(name = "role_id")
    @ManyToOne
    @NonNull
    private Role role;
    @NonNull
    @Column(name = "registration_date")
    private Date registrationDate;

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", user=" + user +
                ", role=" + role +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
