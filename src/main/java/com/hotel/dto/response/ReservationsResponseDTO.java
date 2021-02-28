package com.hotel.dto.response;

import java.time.LocalDate;

import com.hotel.core.DTOEntity;
import com.hotel.entity.Guest;
import com.hotel.entity.Room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationsResponseDTO implements DTOEntity{
	   
	  private Integer reservationId;
	  
	  private LocalDate checkInDate;
	 
	  private LocalDate checkOutDate;
	  
	  private int adults;
	  
	  private int children;
	 
	  private GuestBasicInfoResponseDTO guest;
}
