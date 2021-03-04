package com.hotel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hotel.core.Mapper;
import com.hotel.dto.request.GuestRequestDTO;
import com.hotel.dto.request.ReservationRequestDTO;
import com.hotel.dto.response.GuestBasicInfoResponseDTO;
import com.hotel.dto.response.GuestReservationResponseDTO;
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
		    	  if(reservationRequestDTO.getCheckInDate().after(reservationRequestDTO.getCheckOutDate())) {
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
		     try{
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
		     catch(Exception ex) {
		    	 System.out.println(ex.getMessage());
		    	 throw ex;
		     }
	   }
	   
	   
	   
	   public boolean deleteGuest(int guestId) {
		      try {
		    	this.guestRepository.deleteById(guestId);  
		    	return true;
		      }
		      catch(Exception ex) {
		    	  System.out.println(ex.getMessage());
		    	  throw ex;
		      }
	   }
	   
	   public List<GuestBasicInfoResponseDTO>  retriveAllGuests(Pageable page) {
		   	  try {
		   		  List<GuestBasicInfoResponseDTO> guestList = new ArrayList<>();
		   		  Page<Guest> guests = this.guestRepository.findAll(page);
		   		  if(!guests.isEmpty()) {
		   			  guests.forEach(guest -> {		   				  
		   				  guestList.add((GuestBasicInfoResponseDTO) this.dtoUtil.convertToDto(guest, new GuestBasicInfoResponseDTO()));
		   			  });
		   		  }
		   		  return guestList;
		   	  }
		   	  catch(Exception ex) {		   		  
		   		  throw ex;  
		   	  }
	   }
	   
	   public GuestReservationResponseDTO retriveGuestRelatedReseravations(int guestId) {
		      try {
		    	   GuestReservationResponseDTO guestReservationResponseDTO = null;
		    	   Guest guest = this.guestRepository.findById(guestId).orElse(null);
		    	   if(!guest.equals(null)) {
		    		   guestReservationResponseDTO = (GuestReservationResponseDTO) this.dtoUtil.convertToDto(guest, new GuestReservationResponseDTO());
		    	   }
		    	   return guestReservationResponseDTO;
		    	   
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
}
