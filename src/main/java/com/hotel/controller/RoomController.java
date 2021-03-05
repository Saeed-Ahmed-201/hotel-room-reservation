package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.dto.request.RoomRequestDTO;
import com.hotel.dto.request.RoomTypeMultipleRequestDTO;
import com.hotel.dto.request.RoomTypeRequestDTO;
import com.hotel.dto.response.RoomResponseDTO;
import com.hotel.dto.response.RoomStatusResponseDTO;
import com.hotel.dto.response.RoomTypeResponseDTO;
import com.hotel.service.RoomService;

@RestController
@RequestMapping(value = "/room")
public class RoomController {
	
	   @Autowired
	   private RoomService roomService;

	   @PostMapping("/add-room-type")
	   public ResponseEntity<RoomTypeResponseDTO> addNewRoomType(@RequestParam(name = "type") String type) {
		      try {
		    	  RoomTypeResponseDTO roomType = this.roomService.addNewRoomType(type);
		    	  return ResponseEntity.ok(roomType);
		      }
		      catch(Exception ex) {
		    	  System.out.println("Can't add new type");
		    	  throw ex;
		      }
	   }
	   
	   
	   @PutMapping(value = "/update-room-type")
	   public ResponseEntity<RoomTypeResponseDTO> updateRoomType(@RequestBody RoomTypeRequestDTO roomRequestBody){
		      try {
		    	  RoomTypeResponseDTO roomTypeResponseDTO = this.roomService.updateRoomType(roomRequestBody);
		    	  return ResponseEntity.ok(roomTypeResponseDTO);
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
	   
	   @PostMapping(value = "/add-multiple-room-type")
	   public List<RoomTypeResponseDTO> addMultipleRoomType(@RequestBody RoomTypeMultipleRequestDTO roomTypeList){
		      try {
		    	  List<RoomTypeResponseDTO> roomTypeListResponseDTO = this.roomService.addMultipleRoomType(roomTypeList);
		    	  return roomTypeListResponseDTO;
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
	   
	   @GetMapping(value = "/retrieve-all-room-types")
	   public ResponseEntity<List<RoomTypeResponseDTO>> retreiveAllRoomTypes(){
		      try {
		    	  List<RoomTypeResponseDTO> roomTypeList = this.roomService.retrieveAllRoomType();
		    	  return ResponseEntity.ok(roomTypeList);
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
	   
	   @PostMapping(value = "/add-new-room-status/{roomStatus}")
	   public ResponseEntity<String> addNewRoomStatus(@PathVariable String roomStatus){
		      try {
		    	  boolean feedback = this.roomService.addNewRoomStatus(roomStatus);
		    	  if(feedback) {
		    		  return ResponseEntity.ok("new room status added");
		    	  }
		    	  return ResponseEntity.ok("could not added new room status");
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
	   
	   @PutMapping(value = "/update-room-status/{roomId}/{changeRoomStatus}")
	   public ResponseEntity<String> updateRoomStatus(@PathVariable("roomId") int roomId, @PathVariable("changeRoomStatus") String changeRoomStatus){
		      try {
		    	   boolean feedback = this.roomService.updateRoomStatus(roomId, changeRoomStatus);
		    	   if(!feedback) {
		    		   return ResponseEntity.ok("could not updated");
		    	   }
		    	   return ResponseEntity.ok("successfully updated");
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
	   
	   @GetMapping(value = "/retrieve-all-room-status")
	   public ResponseEntity<?> retrieveAllRoomStatus(){
		      try {
		    	  List<RoomStatusResponseDTO> roomStatus = this.roomService.retrieveAllRoomStatus();
		    	  if(!roomStatus.isEmpty()) {
		    		  return ResponseEntity.ok(roomStatus);
		    	  }
		    	  return ResponseEntity.ok("No room status found");
		      }
		      catch(Exception ex) {
		    	   throw ex;
		      }
	   }
	   
	   @PostMapping(value = "/add-new-room")
	   public ResponseEntity<String> addNewRoom(@RequestBody RoomRequestDTO roomRequestDTO){
		   	  try {
		   		  boolean feedback = this.roomService.addNewRoom(roomRequestDTO);
		   		  if(feedback) {
		   			  return ResponseEntity.ok("successfully room added");
		   		  }
		   		      return ResponseEntity.ok("could not added");
		   	  }
		   	  catch(Exception ex) {
		   		  throw ex;
		   	  }
	   } 
	   
	  @GetMapping(value = "retrive-all-rooms") 
	  public ResponseEntity<?> retrieveAllRooms(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
		     try {
		    	  Pageable pagination = PageRequest.of(page, size); 
		    	  List<RoomResponseDTO> roomList = this.roomService.retrieveAllRooms(pagination);
		    	  if(!roomList.isEmpty()) {
		    		  return ResponseEntity.ok(roomList);
		    	  }
		    	  return ResponseEntity.ok("0 rooms available");
		     } 
		     catch(Exception ex) {
		    	 throw ex;
		     }
		  
	  }
	   
	  @PutMapping(value = "/update-room/{roomId}")
	  public ResponseEntity<String> updateRoom(@PathVariable("roomId") int roomId, @RequestBody RoomRequestDTO roomRequestDTO){
		     try {
		    	  boolean feedback = this.roomService.updateRoom(roomId, roomRequestDTO);
		    	  if(!feedback) {
		    		  return ResponseEntity.ok("could not updated");
		    	  }
		    	      return ResponseEntity.ok("updated successfully");
		     }
		     catch(Exception ex) {
		    	 throw ex;
		     }
	  }
}
