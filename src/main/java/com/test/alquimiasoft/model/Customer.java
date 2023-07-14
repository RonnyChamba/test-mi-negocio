package com.test.alquimiasoft.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String typeIdentification;

    @Column(unique = true, nullable = false)
    private String numberIdentification;

    @Column(nullable = false)
    private String name;

    private String email;

    private String cellphone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subsidiary> subsidiaries = new ArrayList<>();

    public Customer(Integer id, String typeIdentification, String numberIdentification, String name, String email, String cellphone) {
        this.id = id;
        this.typeIdentification = typeIdentification;
        this.numberIdentification = numberIdentification;
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
    }

    public void addSubsidiary(Subsidiary subsidiary) {
        subsidiaries.add(subsidiary);
        subsidiary.setCustomer(this);
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", typeIdentification='" + typeIdentification + '\'' +
                ", numberIdentification='" + numberIdentification + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", subsidiaries=" + subsidiaries.size() +
                '}';
    }
}
