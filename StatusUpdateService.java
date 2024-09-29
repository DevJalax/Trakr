package com.example.transportation.service;

import com.example.transportation.model.StatusUpdate;
import com.example.transportation.repository.StatusUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
