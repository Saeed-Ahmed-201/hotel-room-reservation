package com.hotel.dto.request;

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
public class RoomTypeMultipleRequestDTO implements DTOEntity{
	 private List<String> roomTypeName;
}
