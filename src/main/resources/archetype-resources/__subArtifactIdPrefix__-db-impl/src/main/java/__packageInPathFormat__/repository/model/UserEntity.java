package ${package}.repository.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, updatable = false)
    private String login;

    @Column(unique = true, updatable = false)
    private String email;

    @Column
    private String lastName;

    @Column
    private String firstName;

    @Column
    @Enumerated(EnumType.STRING)
    private Sex sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) &&
                       Objects.equals(login, that.login) &&
                       Objects.equals(email, that.email) &&
                       Objects.equals(lastName, that.lastName) &&
                       Objects.equals(firstName, that.firstName) &&
                       sex == that.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, email, lastName, firstName, sex);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                       "id=" + id +
                       ", login='" + login + '\'' +
                       ", email='" + email + '\'' +
                       ", lastName='" + lastName + '\'' +
                       ", firstName='" + firstName + '\'' +
                       ", sex=" + sex +
                       '}';
    }
}
