package jchat.db.dataSet;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "groupuser")
public class GroupUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGroupUser")
    private int idGroupUser;

    @Column(name = "regDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "idGroup")
    private Group group;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private User user;

    public GroupUser() {
    }

    public GroupUser(Date regDate, Group group, User user) {
        this.regDate = regDate;
        this.group = group;
        this.user = user;
    }

    public int getIdGroupUser() {
        return idGroupUser;
    }

    public void setIdGroupUser(int idGroupUser) {
        this.idGroupUser = idGroupUser;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "GroupUser{" +
                "idGroupUser=" + idGroupUser +
                ", regDate='" + regDate + '\'' +
                '}';
    }
}
