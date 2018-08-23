package com.accenture.interviewproj.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.interviewproj.dtos.TrackingDTO;
import com.accenture.interviewproj.entities.Tracking;
import com.accenture.interviewproj.enums.Track;
import com.accenture.interviewproj.exceptions.IdNotFoundException;
import com.accenture.interviewproj.services.TrackingService;

@RestController
@RequestMapping("/tracking")
@CrossOrigin
public class TrackingContrller {
	
	@Autowired
	private TrackingService trackingService;
	
	@GetMapping("/{candidateId}")
	public ResponseEntity<?> findByCandidateIdAndUserId(@PathVariable Long candidateId){
		try {
			List<Tracking> trackings = trackingService.findTrackingByCandidate(candidateId);
			return ResponseEntity.ok(trackings);
		} catch (IdNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<?> insertTracking(@RequestBody TrackingDTO trackingDto){
		trackingService.insertTracking(trackingDto);
		return ResponseEntity.ok("Inserted");
	}

}
