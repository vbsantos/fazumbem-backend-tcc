package com.rafael.fazumbem.repositories;

import com.rafael.fazumbem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    List<User> findByGroup(String group);

    @Transactional
    void deleteByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User SET verified = true WHERE username = ?1")
    void enableUser(String username);
}
