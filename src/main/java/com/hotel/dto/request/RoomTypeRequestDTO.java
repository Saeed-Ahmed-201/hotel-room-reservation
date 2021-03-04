package com.hotel.dto.request;

import java.util.List;

import com.hotel.core.DTOEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RoomTypeRequestDTO implements DTOEntity{

	 private Integer roomTypeId;
	    private String roomTypeName;
	   

}
