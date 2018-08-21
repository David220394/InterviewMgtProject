package com.accenture.interviewproj.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.interviewproj.dtos.TrackingDTO;
import com.accenture.interviewproj.entities.Tracking;
import com.accenture.interviewproj.enums.Track;
import com.accenture.interviewproj.exception.IdNotFoundException;
import com.accenture.interviewproj.repositories.CandidateRepository;
import com.accenture.interviewproj.repositories.EmployeeRepository;
import com.accenture.interviewproj.repositories.TrackingRepository;

@Service
public class TrackingService {
	
	@Autowired
	private TrackingRepository trackingRepository;

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private CandidateRepository candidateRepository;

	public List<Tracking> findTrackingByCandidate(Long candidateId) throws IdNotFoundException {
		List<Tracking> trackings = trackingRepository.findByCandidateId(candidateId);
		if(!trackings.isEmpty()) {
			return trackings;
		}else {
			throw new IdNotFoundException("Candidate ID not found");
		}
		
	}
	
	public void insertTracking(TrackingDTO trackingDto) {
		Tracking tracking = new Tracking();
		tracking.setComment(tracking.getComment());
		tracking.setTrackingType(Track.valueOf(trackingDto.getTrackingType()));
		tracking.setCandidate(candidateRepository.getOne(trackingDto.getCandidate()));
		tracking.setEmployee(employeeRepository.getOne(trackingDto.getEmployee()));
		trackingRepository.save(tracking);
	}
	
	@PostConstruct
	public void init() {
		if(trackingRepository.findByCandidateId((long) 4) == null) {
		Tracking tracking = new Tracking();
		tracking.setComment("Test");
		tracking.setTrackingType(Track.valueOf("CALL"));
		tracking.setEmployee(employeeRepository.getOne("sylvio.brandon.david"));
		tracking.setCandidate(candidateRepository.getOne((long) 4));
		trackingRepository.save(tracking);
		}
	}
	
}
