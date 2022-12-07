package com.rafael.fazumbem.repositories;

import com.rafael.fazumbem.models.Campaign;
import com.rafael.fazumbem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    @Query("SELECT c FROM Campaign c WHERE c.user.username = ?1")
    List<Campaign> findByUsername(String username);
}
