package com.hotel.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "guest")
public class Guest {
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Integer id;
	   
	   
	   @Column(name = "first_name", length = 100)
	   private String firstName;
	   
	   @Column(name = "last_name", length = 100)
	   private String lastName;
	   
	   @Column(name = "address")
	   @Lob
	   private String address;
	   
	   @Column(name = "email", unique = true, length = 250)
	   private String email;
	   
	   @Column(name = "phone", length = 100)
	   private String phone;
	   
	   @Column(name = "city", length = 100)
	   private String city;
	   
	   @Column(name = "country", length = 100)
	   private String country;
	   
	   @Column(name = "driver_license", length = 100)
	   private String driverLicense;
	   
	   @OneToMany(mappedBy = "guest")
	   @JsonManagedReference
	   private List<Reservation> reservation;
	   

}
