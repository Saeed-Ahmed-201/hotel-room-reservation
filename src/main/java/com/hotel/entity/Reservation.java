package com.hotel.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "reservation_id")
	  private Integer reservationId;
	  
	  @Column(name = "check_in_date")
	  @Temporal(TemporalType.DATE)
	  private Date checkInDate;
	  @Column(name = "check_out_date")
	  @Temporal(TemporalType.DATE)
	  private Date checkOutDate;
	  
	  @Column(name = "adults")
	  private int adults;
	  @Column(name = "children")
	  private int children;
	 
	  @ManyToOne
	  @JoinColumn(name = "guest_id")
	  @JsonBackReference
	  private Guest guest;

	  @ManyToOne
	  @JoinColumn(name = "room_id")
	  @JsonBackReference
	  private Room room;
	  
	  
	  @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	  @PrimaryKeyJoinColumn
	  @JsonManagedReference
	  private Payment payment;
}
