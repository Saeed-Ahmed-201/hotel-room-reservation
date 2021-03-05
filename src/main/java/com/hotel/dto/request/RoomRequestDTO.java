package com.hotel.dto.request;

import com.hotel.core.DTOEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomRequestDTO implements DTOEntity{
	
	   private int roomStatusId;
	   private int otherTypeId;
	   private int roomTypeId;
}
