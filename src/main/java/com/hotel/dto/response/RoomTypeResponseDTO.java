package com.hotel.dto.response;

import java.util.List;

import com.hotel.core.DTOEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoomTypeResponseDTO implements DTOEntity{

	   private Integer roomTypeId;
	   private String roomTypeName;
	   


}
