package com.test.alquimiasoft.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subsidiaries")
public class Subsidiary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String province;
    private String city;
    @Column(nullable = false)
    private String address;
    @Column(columnDefinition = "boolean default false")
    private Boolean main;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Subsidiary(Integer id, String province, String city, String address, Boolean main) {
        this.id = id;
        this.province = province;
        this.city = city;
        this.address = address;
        this.main = main;
    }

    @Override
    public String toString() {
        return "Subsidiary{" +
                "id=" + id +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", isMatriz=" + main +
                ", customer=" + customer.getId() +
                '}';
    }
}
