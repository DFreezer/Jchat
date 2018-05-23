package jchat.db.dataSet;

import javax.persistence.*;

@Entity
@Table(name = "userrole")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUserRole")
    private int idUserRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRole")
    private Role role;

    public UserRole() {
    }

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public int getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(int idUserRole) {
        this.idUserRole = idUserRole;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "idUserRole=" + idUserRole +
                '}';
    }
}
