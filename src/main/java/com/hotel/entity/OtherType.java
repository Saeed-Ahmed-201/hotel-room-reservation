package com.hotel.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "other_type")
public class OtherType {

	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   @Column(name = "other_type_id")
	   private Integer otherTypeId;
	   
	   @Column(name = "other_type_name", length = 100)
	   private String otherTypeName;
	   
	   @Column(name = "base_price")
	   private double basePrice;
	   
	   @OneToMany(mappedBy = "otherType")
	   private List<Room> room;
}
