package com.hotel.dto.request;

import com.hotel.core.DTOEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GuestRequestDTO implements DTOEntity {

   
	   private String firstName;
	   
	   private String lastName;
	   
	   private String address;
	   
	   private String email;
	   
	   private String phone;
	   
	   private String city;
	   
	   private String country;
	   
	   private String driverLicense;
	   
		
}
