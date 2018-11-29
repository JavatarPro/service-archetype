package ${package}.service.domain;


import java.util.Objects;

public class UserBO {
    private Long id;
    private String login;
    private String lastName;
    private String firstName;
    private String email;
    private SexBO sex;

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

    public SexBO getSex() {
        return sex;
    }

    public void setSex(SexBO sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBO userBO = (UserBO) o;
        return Objects.equals(id, userBO.id) &&
                       Objects.equals(login, userBO.login) &&
                       Objects.equals(lastName, userBO.lastName) &&
                       Objects.equals(firstName, userBO.firstName) &&
                       Objects.equals(email, userBO.email) &&
                       sex == userBO.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, lastName, firstName, email, sex);
    }

    @Override
    public String toString() {
        return "UserBO{" +
                       "id=" + id +
                       ", login='" + login + '\'' +
                       ", lastName='" + lastName + '\'' +
                       ", firstName='" + firstName + '\'' +
                       ", email='" + email + '\'' +
                       ", sex=" + sex +
                       '}';
    }
}
