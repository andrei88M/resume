package com.example.resume.repository;

import com.example.resume.model.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {

    @Query("select p from Profile p where p.user.username = ?1")
    Set<Profile> findAllByUsername(String username);

    List<Profile> findAllByUser_Username(String username);

    void deleteById(Long id);
}
