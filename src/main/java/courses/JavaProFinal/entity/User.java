package courses.JavaProFinal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    private long id;
    @Column(name = "user_name")
    private  String name;
    @Column(name = "possible_payments")
    private double possiblePayments = 10000.00;

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }
}
