package jchat.db.dataSet;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "groupmessage")
public class GroupMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGroupMessage")
    private int idGroupMessage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idMessage")
    private Message message;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "idGroup")
    @JsonIgnore
    private Group group;

    public GroupMessage() {
    }

    public GroupMessage(Message message, Group group) {
        this.message = message;
        this.group = group;
    }

    public int getIdGroupMessage() {
        return idGroupMessage;
    }

    public void setIdGroupMessage(int idGroupMessage) {
        this.idGroupMessage = idGroupMessage;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "GroupMessage{" +
                "idGroupMessage=" + idGroupMessage +
                ", message=" + message +
                '}';
    }
}
