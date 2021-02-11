package ru.sapteh.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name",length = 50,nullable = false)
    @NonNull
    private String firstName;
    @Column(name = "last_name",length = 50,nullable = false)
    @NonNull
    private String lastName;
    @Column(length = 50,nullable = false)
    @NonNull
    private String email;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
    private Set<UserRole> roles;

    public Date getLastDate(){
        Iterator<UserRole> iterator=roles.iterator();
        Date date=new Date(0);
        for (int i = 0; i < getSizeRole(); i++) {
            Date date1=iterator.next().getRegistrationDate();
            if (date.getTime()<date1.getTime()){
                date=date1;
            }
        }
        return date;
    }
    @Transient
    private final int sizeRole=getSizeRole();

    private Integer getSizeRole(){
        if (roles==null)roles=new HashSet<>();
        return roles.size();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
