package com.hotel.dto.response;

import java.util.Date;

import com.hotel.core.DTOEntity;
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
	  
	  private Date checkInDate;
	 
	  private Date checkOutDate;
	  
	  private int adults;
	  
	  private int children;
	  
	  
	  private GuestBasicInfoResponseDTO guest;
}
