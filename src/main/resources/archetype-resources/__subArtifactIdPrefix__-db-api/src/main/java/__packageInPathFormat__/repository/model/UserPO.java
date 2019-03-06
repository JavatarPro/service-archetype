package ${package}.repository.model;


import java.util.Objects;

public class UserPO {
    private Long id;
    private String login;
    private String lastName;
    private String firstName;
    private String email;
    private SexPO sex;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SexPO getSex() {
        return sex;
    }

    public void setSex(SexPO sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPO userPO = (UserPO) o;
        return Objects.equals(id, userPO.id) &&
                       Objects.equals(login, userPO.login) &&
                       Objects.equals(lastName, userPO.lastName) &&
                       Objects.equals(firstName, userPO.firstName) &&
                       Objects.equals(email, userPO.email) &&
                       sex == userPO.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, lastName, firstName, email, sex);
    }

    @Override
    public String toString() {
        return "UserPO{" +
                       "id=" + id +
                       ", login='" + login + '\'' +
                       ", lastName='" + lastName + '\'' +
                       ", firstName='" + firstName + '\'' +
                       ", email='" + email + '\'' +
                       ", sex=" + sex +
                       '}';
    }
}
