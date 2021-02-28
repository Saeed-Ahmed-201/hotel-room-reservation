package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.dto.request.MultipleReservationDeleteRequestDTO;
import com.hotel.dto.request.ReservationRequestDTO;
import com.hotel.dto.response.ReservationsResponseDTO;
import com.hotel.service.ReservationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/reservation")
@Api(value = "Reservation Controller", description = "Reservation controller has room reservation endpoint")
public class ReservationController {

	   @Autowired
	   private ReservationService reservationService;
	
	   @ApiOperation(value = "Update the guest reservation that has been created")
	   @PutMapping(value = "/update/{reservationId}")
	   public ResponseEntity<String> updateReservation(@PathVariable("reservationId") int reservationId,@RequestBody ReservationRequestDTO reservationRequestDTO){
		 
		      try {
		    	  String feedback = this.reservationService.updateReservation(reservationId, reservationRequestDTO);
		    	  if(feedback.equals("checkin date can not be greater checkout date")) {
		    		  return ResponseEntity.ok("checkin date can not be greater checkout date");
		    	  }
		    	  else if (feedback.equals("reservation not found")){
		    		  return ResponseEntity.ok("reservation not found");
		    	  }
		    	  else {		    		  
		    		  return ResponseEntity.ok("updated successfully");
		    	  }
		      }
		      catch(Exception ex) {
		    	  System.out.println(ex.getMessage());
		    	  throw ex;
		      }
		   
	   }
     
	   @GetMapping(value = "all-reservations")
	   public ResponseEntity<?> findAllReservations(
			   
			                                        @RequestParam(defaultValue = "0") int page,
			                                        @RequestParam(defaultValue = "3") int size){
		   
		      try {
		    	Pageable pageWithFiveElements = PageRequest.of(page, size, Sort.by("reservationId").descending());
		    	List<ReservationsResponseDTO> reservations = this.reservationService.findAllReservation(pageWithFiveElements);
		    	if(!reservations.isEmpty()) {
		    		return ResponseEntity.ok(reservations);
		    	}
		    	    return ResponseEntity.ok("0 reservations");
		      }
		      catch(Exception ex) {
		    	  System.out.println(ex.getMessage());
		    	  throw ex;
		      }
	   }
	   
	   @GetMapping(value = "/single-reservation/{reservationId}")
	   public ResponseEntity<?> singleReservationById(@PathVariable("reservationId") int reservationId){
		      try {
		    	  ReservationsResponseDTO reservation = this.reservationService.singleReservationById(reservationId);
		    	  if(reservation.getReservationId() != null) {
		    		  return ResponseEntity.ok(reservation);
		    	  }
		    	      return ResponseEntity.ok("0 not reservation record found");
		      }
		      catch(Exception ex) {
		    	  System.out.println(ex.getMessage());
		    	  throw ex;
		      }
	   }
	   
	   
	   @DeleteMapping(value = "/single-reservation-delete/{reservationId}")
	   public ResponseEntity<String> deleteSingleReservation(@PathVariable("reservationId") int reservationId){
		      try {
		    	  boolean isDeleted = this.reservationService.deleteSingleReservation(reservationId);
		    	  if(isDeleted) {
		    		  return ResponseEntity.ok("Deleted successfully");
		    	  }
		    	      return ResponseEntity.ok("reservation does not exists");
		      }
		      catch(Exception ex) {
		    	  System.out.println(ex.getMessage());
		    	  throw ex;
		      }
	   }

	   
	   @DeleteMapping(value = "/multiple-reservation-delete")
	   public ResponseEntity<String> deleteMultipleReservations(@RequestBody MultipleReservationDeleteRequestDTO reservationIds){
		     try {
		    	   boolean isAllDeleted = this.reservationService.deleteMultipleReservations(reservationIds);
		    	   if(isAllDeleted) {
		    		    return ResponseEntity.ok("deleted all");
		    	   }
		    	        return ResponseEntity.ok("could not deleted");
		     }   
		     catch(Exception ex) {
		    	 System.out.println(ex.getMessage());
		    	 throw ex;
		     }
	   }
	
}
