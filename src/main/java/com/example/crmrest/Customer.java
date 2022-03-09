package com.example.crmrest;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name="Customer_Details")
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(name="Name")
    private String name;
    @Column(name="Age")
    private int age;
    @Column(name="Location")
    private String location;
    @Column(name = "Phone")
    private String phone;
    @CreationTimestamp
    private Date created_at;
    @UpdateTimestamp
    private Date updated_at;
}
