package com.hotel.dto.response;

import javax.persistence.Transient;

import com.hotel.core.DTOEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoomResponseDTO implements DTOEntity {
	
	 private Integer roomId;
	 private RoomStatusResponse roomStatus;
	 private OtherTypeResponse  otherType;
	 private RoomTypeResponse   roomType;
	 

}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class OtherTypeResponse {
	   private String otherTypeName;
	   private double basePrice;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class RoomStatusResponse{
	 private String roomStatus;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class RoomTypeResponse{
	   private String roomTypeName;
}