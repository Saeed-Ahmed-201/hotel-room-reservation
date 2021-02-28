package com.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.core.Mapper;
import com.hotel.dto.request.GuestRequestDTO;
import com.hotel.dto.request.ReservationRequestDTO;
import com.hotel.entity.Guest;
import com.hotel.entity.Reservation;
import com.hotel.entity.Room;
import com.hotel.repository.GuestRepository;
import com.hotel.repository.ReservationRepository;
import com.hotel.repository.RoomRepository;

@Service
public class GuestService {

	   @Autowired
	   private GuestRepository guestRepository;
	   
	   @Autowired
	   private RoomRepository roomRepository;
	   
	   @Autowired
	   private ReservationRepository reservationRepository;
	   
	   private Mapper dtoUtil = new Mapper();
	   
	   // add new guest
	   public void addGuest(GuestRequestDTO guestRequestDTO) {
		      try {
		    	  Guest guest = (Guest) dtoUtil.convertToEntity(new Guest(),guestRequestDTO);
		    	  this.guestRepository.save(guest);
		      }
		      catch(Exception ex) {
		    	  System.out.println(ex.getMessage());
		    	  throw ex;
		      }
	   }
	   
	   public boolean doReservation(ReservationRequestDTO reservationRequestDTO) {
		      try {
		    	  boolean feedBack = true;
		    	  if(reservationRequestDTO.getCheckInDate().isAfter(reservationRequestDTO.getCheckOutDate())) {
		    		  feedBack = false;
		    	  }
		    	  else {
				      Reservation reservation = new Reservation();		    	  
				      Room room = this.roomRepository.findById(reservationRequestDTO.getRoomId()).orElse(null);
				      Guest guest = this.guestRepository.findById(reservationRequestDTO.getGuestId()).orElse(null);
				  
				      reservation.setRoom(room);
				      reservation.setGuest(guest);
				      
				      reservation.setAdults(reservationRequestDTO.getAdults());
				      reservation.setChildren(reservationRequestDTO.getChildren());
				      
				      reservation.setCheckInDate(reservationRequestDTO.getCheckInDate());
				      reservation.setCheckOutDate(reservationRequestDTO.getCheckOutDate());
				      
				      this.reservationRepository.save(reservation);		    		  
		    	  }
		    	  return feedBack;
			      
		      }
		      catch (Exception ex) {
		    	  System.out.println(ex.getMessage());
		    	  throw ex;
		      }
	   }
	   
	   public boolean updateGuest(int guestId, GuestRequestDTO guestRequestDTO) {		   
		      boolean feedBack = true;
		      Guest guest = this.guestRepository.findById(guestId).orElse(null);
		      if(!guest.equals(null)) {
		    	  guest.setFirstName(guestRequestDTO.getFirstName());
		    	  guest.setLastName(guestRequestDTO.getLastName());
		    	  guest.setAddress(guestRequestDTO.getAddress());
		    	  guest.setCity(guestRequestDTO.getCity());
		    	  guest.setCountry(guestRequestDTO.getCountry());
		    	  guest.setEmail(guestRequestDTO.getEmail());
		    	  guest.setDriverLicense(guestRequestDTO.getDriverLicense());
		    	  guest.setPhone(guestRequestDTO.getPhone());
		    	  
		    	  this.guestRepository.save(guest);
		    	  
		    	  feedBack = false;
		      }
		      return feedBack;
	   }
	   
	   
}
