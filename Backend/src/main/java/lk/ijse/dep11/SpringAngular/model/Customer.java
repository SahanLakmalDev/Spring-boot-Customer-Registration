package lk.ijse.dep11.SpringAngular.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {
    @Id
    @Column(length = 10)
    private String id;
    @Column(nullable = false, length = 200)
    private String name;
    @Column(nullable = false, length = 300)
    private String address;
    @Column(nullable = false, length = 20, unique = true)
    private String contact;

}
