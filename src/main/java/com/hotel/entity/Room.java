package com.hotel.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "room")
public class Room {

	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   @Column(name = "room_id")
	   private Integer roomId;
	   
	   
	   @ManyToOne
	   @JoinColumn(name = "room_status")
	   private RoomStatus roomStatus;
	   
	   @ManyToOne
	   @JoinColumn(name = "other_type")
	   private OtherType  otherType;
	   
	   @ManyToOne
	   @JoinColumn(name = "room_type")
	   private RoomType   roomType;
	   
	   @OneToMany(mappedBy = "room")
	   @JsonManagedReference
	   private List<Reservation> reservation;

}
