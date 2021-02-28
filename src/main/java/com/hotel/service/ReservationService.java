package com.hotel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hotel.core.Mapper;
import com.hotel.dto.request.MultipleReservationDeleteRequestDTO;
import com.hotel.dto.request.ReservationRequestDTO;
import com.hotel.dto.response.ReservationsResponseDTO;
import com.hotel.entity.Guest;
import com.hotel.entity.Reservation;
import com.hotel.entity.Room;
import com.hotel.repository.GuestRepository;
import com.hotel.repository.ReservationRepository;
import com.hotel.repository.RoomRepository;

@Service
public class ReservationService {

	  @Autowired
	  private ReservationRepository reservationRepository;
	
	  @Autowired
	  private GuestRepository guestRepository;
	  
	  @Autowired
	  private RoomRepository roomRepository;
	  
	  private Mapper dtoUtil = new Mapper();
	  
	  public String updateReservation(int reservationId, ReservationRequestDTO reservationRequestDTO) {
		     
		     try {
		    	 String feedback = "";
		    	 Reservation reservation = this.reservationRepository.findById(reservationId).orElse(null);
                 if(!reservation.equals(null)) {
                	 if(reservationRequestDTO.getCheckInDate().isAfter(reservationRequestDTO.getCheckOutDate())) {
                		 feedback = "checkin date can not be greater checkout date";
                	 }
                	 else {
                		 Guest guest = this.guestRepository.findById(reservationRequestDTO.getGuestId()).orElse(null);
        		    	 Room  room = this.roomRepository.findById(reservationRequestDTO.getRoomId()).orElse(null);

        		    	 reservation.setAdults(reservationRequestDTO.getAdults());
        		    	 reservation.setChildren(reservationRequestDTO.getChildren());
        		    	 
        		    	 reservation.setCheckInDate(reservationRequestDTO.getCheckInDate());
        		    	 reservation.setCheckOutDate(reservationRequestDTO.getCheckOutDate());
        		    	 
        		    	 reservation.setGuest(guest);
        		    	 reservation.setRoom(room);
        		    	 
        		    	 this.reservationRepository.save(reservation);
	 
                	 }
                	}
                 else {
                	 feedback = "reservation not found"; 
                 }
                 return feedback;
		     }
		     catch(Exception ex) {
		    	 System.out.println(ex.getMessage());
		    	 throw ex;
		     }
	  }
	  
	  public List<ReservationsResponseDTO> findAllReservation(Pageable page){
		     try {
		    	List<ReservationsResponseDTO> reservationResponseDTO = new ArrayList<>();
		        Page<Reservation> reservations = this.reservationRepository.findAll(page);
                if(!reservations.isEmpty()) {
                	reservations.forEach(reservation -> {
                		reservationResponseDTO.add((ReservationsResponseDTO) dtoUtil.convertToDto(reservation, new ReservationsResponseDTO()));
                	});
                }
		        return reservationResponseDTO;
		     }
		     catch(Exception ex) {
		    	 System.out.println(ex.getMessage());
		    	 throw ex;
		     }
	  }
	  
	  public ReservationsResponseDTO singleReservationById(int reservationId) {
		     try {
		    	 ReservationsResponseDTO reservationResponseDTO = new ReservationsResponseDTO(); 
		    	 Reservation reservation = this.reservationRepository.findById(reservationId).orElse(null);
		    	 
		    	 if(reservation != null) {		    		 
		    		 reservationResponseDTO = (ReservationsResponseDTO) this.dtoUtil.convertToDto(reservation, new ReservationsResponseDTO());
		    	 }	    		 
		    		 return reservationResponseDTO;
		    	 
		    	 
		     }
		     catch(Exception ex) {
		    	 System.out.println(ex.getMessage());
		    	 throw ex;
		     }
		     
	  }
	    
	  public boolean deleteSingleReservation(int reservationId) {
		     try {
		    	 this.reservationRepository.deleteById(reservationId);
		    	 return true;
		     }
		     catch(Exception ex) {
		    	 System.out.println(ex.getMessage());
		    	 throw ex;
		     }
	  }
	  
	  public boolean deleteMultipleReservations(MultipleReservationDeleteRequestDTO reservationIds) {
		     try {
		    	 if(!reservationIds.equals(null)) {
		    		 reservationIds.getReservationIds().forEach(reservationId-> {
		    			 this.reservationRepository.deleteById(reservationId);
		    		 });
		    		 return true;
		    	 }
		    	 return false;
		     }
		     catch(Exception ex) {
		    	 System.out.println(ex.getMessage());
		    	 throw ex;
		     }
		  
	  }
}
