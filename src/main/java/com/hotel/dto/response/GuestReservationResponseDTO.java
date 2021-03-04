package com.hotel.dto.response;

import java.util.List;

import com.hotel.core.DTOEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GuestReservationResponseDTO implements DTOEntity {
	 
	   private Integer id;
	   
	   
	   private String firstName;
	   
	   private String lastName;
	   
	   private String email;
	   
	   private String phone;
	   
	   private List<ReservationByGuestResponseDTO> reservation;
}
