package com.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.dto.request.GuestRequestDTO;
import com.hotel.dto.request.ReservationRequestDTO;
import com.hotel.service.GuestService;

@RestController
@RequestMapping(value = "/guest")
public class GuestController {
	
	    @Autowired
	    private GuestService guestService;
	    
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
	    		       boolean feedBack = this.guestService.doReservation(reservationRequestDTO);
	    		       if(!feedBack) {
	    		    	  return ResponseEntity.ok("checkin date is can't be greater then checkout date...");   
	    		       }
	    		       return ResponseEntity.ok("successfully reserved");
	    	   }
	    	   catch(Exception ex) {
	    		   System.out.println(ex.getMessage());
	    		   throw ex;
	    	   }
	    }
	    
	    
	    @PutMapping(value = "/update-guest")
	    public ResponseEntity<String> updateGuest(@PathVariable("guestID") int guestId, @RequestBody GuestRequestDTO guestRequestDTO){
	           try {
	        	   boolean feedBack = this.guestService.updateGuest(guestId, guestRequestDTO);
	        	   if(!feedBack) {
	        		   return ResponseEntity.ok("could not updated guest");
	        	   }
	        	       return ResponseEntity.ok("guest updated successfully");
	           }
	           catch(Exception ex) {
	        	   System.out.println(ex.getMessage());
	        	   throw ex;
	           }
	    }


}
