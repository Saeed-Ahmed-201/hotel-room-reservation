package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.dto.request.GuestRequestDTO;
import com.hotel.dto.request.ReservationRequestDTO;
import com.hotel.dto.response.GuestBasicInfoResponseDTO;
import com.hotel.dto.response.GuestReservationResponseDTO;
import com.hotel.service.GuestService;

@RestController
@RequestMapping(value = "/guest")
public class GuestController {
	
	    @Autowired
	    private GuestService guestService;
	    
	    @GetMapping("/retrieve-all")
	    public ResponseEntity<List<GuestBasicInfoResponseDTO>> retrieveAllGuests(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
	    	   try {
	    		   Pageable pageResults = PageRequest.of(page, size, Sort.by("firstName").ascending());
	    		   List<GuestBasicInfoResponseDTO> guestList = this.guestService.retriveAllGuests(pageResults);
	    		   return ResponseEntity.ok(guestList);
	    	   }
	    	   catch(Exception ex) {
	    		   throw ex;
	    	   }
	    }
	    
	    
	    @PostMapping(value = "/add-guest")
	    public ResponseEntity<String> addGuest(@RequestBody GuestRequestDTO guest){
	    	   try {
	    	          this.guestService.addGuest(guest);
	    		       return ResponseEntity.ok("successfully added");
	    	   }
	    	   catch(Exception ex) {
	    		   System.out.println(ex.getMessage());
	    		   throw ex;
	    	   }
	    }
	    
	    @PostMapping(value = "/reservation")
	    public ResponseEntity<String> doReservation(@RequestBody ReservationRequestDTO reservationRequestDTO){
	    	   try {
	    		       boolean feedback = this.guestService.doReservation(reservationRequestDTO);
	    		       if(!feedback) {
	    		    	  return ResponseEntity.ok("checkin date is can't be greater then checkout date...");   
	    		       }
	    		       return ResponseEntity.ok("successfully reserved");
	    	   }
	    	   catch(Exception ex) {
	    		   System.out.println(ex.getMessage());
	    		   throw ex;
	    	   }
	    }
	    
	    
	    @PutMapping(value = "/update-guest/{guestId}")
	    public ResponseEntity<String> updateGuest(@PathVariable("guestId") int guestId, @RequestBody GuestRequestDTO guestRequestDTO){
	           try {
	        	   boolean feedback = this.guestService.updateGuest(guestId, guestRequestDTO);
	        	   if(!feedback) {
	        		   return ResponseEntity.ok("could not updated guest");
	        	   }
	        	       return ResponseEntity.ok("guest updated successfully");
	           }
	           catch(Exception ex) {
	        	   System.out.println(ex.getMessage());
	        	   throw ex;
	           }
	    }
	    
	    @DeleteMapping(value = "/delete-guest/{guestId}")
	    public ResponseEntity<String> deleteSingleGuest(@PathVariable("guestId") int guestId){
	    	   try {
	    		   boolean feedback = this.guestService.deleteGuest(guestId);
	    		   if(feedback) {
	    			   return ResponseEntity.ok("guest deleted successfully");
	    		   }
	    		   return ResponseEntity.ok("guest could not deleted");
	    	   }
	    	   catch(Exception ex) {
	    		   System.out.println(ex.getMessage());
	    		   throw ex;
	    	   }
	    }
	    
        @GetMapping(value = "/guest-reservations/{guestId}")	    
	    public ResponseEntity<?> retriveGuestRelatedReseravations(@PathVariable("guestId") int guestId) {
	    	   try {
	    		   GuestReservationResponseDTO guestReservationResponseDTO = this.guestService.retriveGuestRelatedReseravations(guestId);
	    		   if(guestReservationResponseDTO.equals(null)) {
	    			   return ResponseEntity.ok("no reservation found");
	    		   }
	    		   return ResponseEntity.ok(guestReservationResponseDTO);
	    	   }
	    	   catch(Exception ex) {
	    		   throw ex;    
	    	   }
	    }


}
