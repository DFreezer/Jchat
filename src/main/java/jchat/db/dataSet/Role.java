package jchat.db.dataSet;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRole")
    private int idRole;

    @Column(name = "type")
    private String type;

    public Role() {
    }

    public Role(String type) {
        this.type = type;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Role{" +
                "idRole=" + idRole +
                ", type='" + type + '\'' +
                '}';
    }
}
