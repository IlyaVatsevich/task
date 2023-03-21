package by.ilya_vatsevich.bootcamp_task.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import java.util.Set;

@Entity
@NoArgsConstructor
@Builder(setterPrefix = "with")
@AllArgsConstructor
@Getter
@Setter
@Table(name = "usr")
public class User {

    @RequiredArgsConstructor
    @Getter
    public enum UserRole {
        ADMINISTRATOR("Administrator"),
        SALE_USER("Sale User"),
        CUSTOMER_USER("Customer User"),
        SECURE_API_USER("Secure API User");

        private final String idName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "patronymic",nullable = false)
    private String patronymic;

    @Column(name = "email",nullable = false)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER,targetClass = UserRole.class)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false))
    @Enumerated(EnumType.STRING)
    @Column(name = "r_name")
    private Set<UserRole> userRoles;

}
