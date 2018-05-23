package jchat.db.dataSet;

import javax.persistence.*;

@Entity
@Table(name = "userinfo")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUserInfo")
    private int idUserInfo;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private User user;

    public UserInfo() {
    }

    public UserInfo(String firstName, String lastName, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
    }

    public int getIdUserInfo() {
        return idUserInfo;
    }

    public void setIdUserInfo(int idUserInfo) {
        this.idUserInfo = idUserInfo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "idUserInfo=" + idUserInfo +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
