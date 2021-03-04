package com.hotel.dto.response;

import java.util.Date;

import com.hotel.core.DTOEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationByGuestResponseDTO implements DTOEntity {
	  private Integer reservationId;
	  
	  private Date checkInDate;
	  private Date checkOutDate;
	  
	  private int adults;
	  private int children;
}
