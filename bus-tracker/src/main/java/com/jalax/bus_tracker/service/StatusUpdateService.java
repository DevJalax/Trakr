package com.jalax.bus_tracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jalax.bus_tracker.entity.StatusUpdate;
import com.jalax.bus_tracker.repo.StatusUpdateRepository;

@Service
public class StatusUpdateService {

	    @Autowired
	    private StatusUpdateRepository statusUpdateRepository;

	    public List<StatusUpdate> getAllUpdates() {
	        return statusUpdateRepository.findAll();
	    }

	    public StatusUpdate saveStatusUpdate(StatusUpdate statusUpdate) {
	        return statusUpdateRepository.save(statusUpdate);
	    }
}
