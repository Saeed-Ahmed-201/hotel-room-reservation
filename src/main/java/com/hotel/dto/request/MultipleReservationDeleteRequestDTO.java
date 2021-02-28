package com.hotel.dto.request;

import java.util.List;

import com.hotel.core.DTOEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MultipleReservationDeleteRequestDTO implements DTOEntity  {
	   private List<Integer> reservationIds;
}
