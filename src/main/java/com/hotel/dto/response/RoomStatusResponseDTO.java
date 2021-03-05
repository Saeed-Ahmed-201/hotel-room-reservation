package com.hotel.dto.response;

import com.hotel.core.DTOEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomStatusResponseDTO implements DTOEntity {
	   private Integer roomStatusId;
	   private String roomStatus;
}
