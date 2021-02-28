package com.hotel.dto.response;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Lob;

import com.hotel.core.DTOEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GuestBasicInfoResponseDTO implements DTOEntity {

	   private Integer id;
	
	   private String firstName;
	   
	   private String lastName;
	   
	   private String address;
	   
	   private String email;
	   
	   private String phone;
	   
	   private String city;
	   
	   private String country;
	   
	   private String driverLicense;
}
