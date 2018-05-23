package jchat.db.dataSet;

import javax.persistence.*;

@Entity
@Table(name = "usercontact")
public class UserContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUserContact")
    private int idUserContact;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idContact")
    private User contact;

    public UserContact() {
    }

    public UserContact(User user, User contact) {
        this.user = user;
        this.contact = contact;
    }

    public int getIdUserContact() {
        return idUserContact;
    }

    public void setIdUserContact(int idUserContact) {
        this.idUserContact = idUserContact;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getContact() {
        return contact;
    }

    public void setContact(User contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "UserContact{" +
                "idUserContact=" + idUserContact +
                '}';
    }
}
