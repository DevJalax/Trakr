package com.jalax.bus_tracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jalax.bus_tracker.entity.StatusUpdate;

@Repository
public interface StatusUpdateRepository extends JpaRepository<StatusUpdate, Long>{

}
