package com.hotel.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hotel.keys.PaymentKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {
	
	   @EmbeddedId
	   private PaymentKey id;
	   
	   @OneToOne
	   @JoinColumn(name = "reservation_id")
	   @JsonBackReference
	   private Reservation reservation;
	   
	   @Column(name = "first_name")
	   private String firstName;
	   
	   @Column(name = "last_name")
	   private String lastName;
	   

}
