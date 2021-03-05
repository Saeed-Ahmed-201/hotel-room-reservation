package com.hotel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hotel.core.Mapper;
import com.hotel.dto.request.RoomRequestDTO;
import com.hotel.dto.request.RoomTypeMultipleRequestDTO;
import com.hotel.dto.request.RoomTypeRequestDTO;
import com.hotel.dto.response.RoomResponseDTO;
import com.hotel.dto.response.RoomStatusResponseDTO;
import com.hotel.dto.response.RoomTypeResponseDTO;
import com.hotel.entity.OtherType;
import com.hotel.entity.Room;
import com.hotel.entity.RoomStatus;
import com.hotel.entity.RoomType;
import com.hotel.repository.OtherTypeRepository;
import com.hotel.repository.RoomRepository;
import com.hotel.repository.RoomStatusRepository;
import com.hotel.repository.RoomTypeRepository;

@Service
public class RoomService {
	
	  
	   @Autowired
	   private RoomTypeRepository roomTypeRepository;
	   
	   @Autowired
	   private RoomStatusRepository roomStatusRepository;
	   
	   @Autowired
	   private OtherTypeRepository otherTypeRepository;
	   
	   @Autowired
	   private RoomRepository roomRepository;
	   
	   
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
	   
	   public boolean addNewRoomStatus(String newRoomStatus) {
		      try {
		    	  RoomStatus roomStatus = new RoomStatus();
		    	  roomStatus.setRoomStatus(newRoomStatus);
		    	  this.roomStatusRepository.save(roomStatus);
		    	  return true;
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
	   
	   public boolean updateRoomStatus(int roomId, String changeRoomStatus) {
		      try {
		    	  RoomStatus roomStatus = this.roomStatusRepository.findById(roomId).orElse(null);
		    	  if(!roomStatus.equals(null)) {
		    		  roomStatus.setRoomStatus(changeRoomStatus);
		    		  this.roomStatusRepository.save(roomStatus);
		    		  return true;
		    	  }
		    	  return false;
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
	   
	   public List<RoomStatusResponseDTO> retrieveAllRoomStatus(){
		      try {
		    	  List<RoomStatusResponseDTO> roomStatusList = new ArrayList<>();
                  List<RoomStatus> roomStatusRecords =  this.roomStatusRepository.findAll();
                  if(!roomStatusRecords.isEmpty()) {
                	  roomStatusRecords.forEach(roomStatus -> {
                		  roomStatusList.add((RoomStatusResponseDTO) this.dtoUtil.convertToDto(roomStatus, new RoomStatusResponseDTO()));
                	  });
                  }
		    	  return roomStatusList;
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
	   
	   public boolean addNewRoom(RoomRequestDTO addNewRoom) {
		      try {
		    	  Room room = new Room();
		    	  RoomType roomType = this.roomTypeRepository.findById(addNewRoom.getRoomTypeId()).orElse(null);
		    	  RoomStatus roomStatus = this.roomStatusRepository.findById(addNewRoom.getRoomStatusId()).orElse(null);
		    	  OtherType otherType = this.otherTypeRepository.findById(addNewRoom.getOtherTypeId()).orElse(null);
		    	  
		    	  room.setOtherType(otherType);
		    	  room.setRoomStatus(roomStatus);
		    	  room.setRoomType(roomType);
		    	  
		    	  this.roomRepository.save(room);
		    	  return true;
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
	   
	   
	   public boolean updateRoom(int roomId, RoomRequestDTO updateRoomRequestDTO) {
		      try {
		    	  Room room = this.roomRepository.findById(roomId).orElse(null);
		    	  if(!room.equals(null)) {
		    		  
		    		  RoomType roomType = this.roomTypeRepository.findById(updateRoomRequestDTO.getRoomTypeId()).orElse(null);
		    		  RoomStatus roomStatus = this.roomStatusRepository.findById(updateRoomRequestDTO.getRoomStatusId()).orElse(null);
		    		  OtherType otherType = this.otherTypeRepository.findById(updateRoomRequestDTO.getOtherTypeId()).orElse(null);
                      
		    		  room.setRoomType(roomType);
		    		  room.setRoomStatus(roomStatus);
		    		  room.setOtherType(otherType);
		    		  
		    		  this.roomRepository.save(room);
		    		  return true;
		    	  }
		    	  return false;
		    	  
		      }   
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }
	   
	   public List<RoomResponseDTO> retrieveAllRooms(Pageable page){
		      try {
		    	  List<RoomResponseDTO> roomResponseDTO = new ArrayList<>();
		    	  Page<Room> rooms = this.roomRepository.findAll(page);
		    	  if(!rooms.isEmpty()) {
		    		  rooms.forEach(room -> {
		    			  roomResponseDTO.add((RoomResponseDTO) this.dtoUtil.convertToDto(room, new RoomResponseDTO()));
		    		  });
		    	  }
		    	  return roomResponseDTO;
		      }
		      catch(Exception ex) {
		    	  throw ex;
		      }
	   }

}
