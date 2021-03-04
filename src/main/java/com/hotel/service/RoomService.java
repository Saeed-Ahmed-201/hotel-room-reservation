package com.hotel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hotel.core.Mapper;
import com.hotel.dto.request.RoomTypeMultipleRequestDTO;
import com.hotel.dto.request.RoomTypeRequestDTO;
import com.hotel.dto.response.RoomTypeResponseDTO;
import com.hotel.entity.RoomType;
import com.hotel.repository.RoomTypeRepository;

@Service
public class RoomService {
	
	  
	   @Autowired
	   private RoomTypeRepository roomTypeRepository;
	   
	   private Mapper dtoUtil = new Mapper();
	   
	   public RoomTypeResponseDTO addNewRoomType(String type)  {
		      try {
		    	  RoomType roomType = new RoomType();
		    	  roomType.setRoomTypeName(type);
                  return (RoomTypeResponseDTO) this.dtoUtil.convertToDto(this.roomTypeRepository.save(roomType), new RoomTypeResponseDTO());
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
	   
	   public RoomTypeResponseDTO updateRoomType(RoomTypeRequestDTO roomType) {
		      try {
		    	  RoomType room = this.roomTypeRepository.findById(roomType.getRoomTypeId()).orElse(null);
		    	  room.setRoomTypeName(roomType.getRoomTypeName());
		    	  RoomTypeResponseDTO roomTypeResponseDTO = (RoomTypeResponseDTO) dtoUtil.convertToDto(this.roomTypeRepository.save(room), new RoomTypeResponseDTO());
		    	  return roomTypeResponseDTO;
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
	   
	   public List<RoomTypeResponseDTO> addMultipleRoomType(RoomTypeMultipleRequestDTO roomMultipleType){
		      try {
		    	  List<RoomTypeResponseDTO> roomTypeList = new ArrayList<>();
		    	  if(roomMultipleType != null) {
		    		  roomMultipleType.getRoomTypeName().forEach(roomType -> {
		    			  	RoomType room = new RoomType();
		    			  	room.setRoomTypeName(roomType);
		    			  	roomTypeList.add((RoomTypeResponseDTO) this.dtoUtil.convertToDto(this.roomTypeRepository.save(room),new RoomTypeResponseDTO()));
		    		  });
		    	  }
		    	  return roomTypeList;
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
	   
	   
	   public List<RoomTypeResponseDTO>  retrieveAllRoomType(){
		      try {
		    	  List<RoomTypeResponseDTO> roomTypeList = new ArrayList<>();
		    	  List<RoomType> roomTypes = this.roomTypeRepository.findAll();
		    	  if(!roomTypes.isEmpty()) {
		    		  roomTypes.forEach(roomType -> {
		    			   roomTypeList.add((RoomTypeResponseDTO) dtoUtil.convertToDto(roomType, new RoomTypeResponseDTO()));
		    		  });
		    	  }
		    	  return roomTypeList;
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }

}
