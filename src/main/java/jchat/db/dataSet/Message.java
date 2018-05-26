package jchat.db.dataSet;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMessage")
    private int idMessage;

    @Column(name = "body", columnDefinition = "longtext")
    private String body;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSender")
    private User sender;

    public Message() {
    }

    public Message(String body, Date date, User sender) {
        this.body = body;
        this.date = date;
        this.sender = sender;
    }

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Message{" +
                "idMessage=" + idMessage +
                ", body='" + body + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
