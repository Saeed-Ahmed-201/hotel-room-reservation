package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.dto.request.RoomTypeMultipleRequestDTO;
import com.hotel.dto.request.RoomTypeRequestDTO;
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
	   
}
