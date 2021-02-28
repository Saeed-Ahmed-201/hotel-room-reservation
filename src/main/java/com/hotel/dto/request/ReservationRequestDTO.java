package com.hotel.dto.request;

import java.time.LocalDate;

import com.hotel.core.DTOEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationRequestDTO implements DTOEntity{
	  
	  private int reservationId;
	  
	  private LocalDate checkInDate;
	  private LocalDate checkOutDate;	  

	  private int adults;
	  private int children;
	  
	  private Integer guestId;
	  private Integer roomId;
	  
	  
}
