package com.lathief.profile.repository;

import com.lathief.profile.model.Tag;
import com.lathief.profile.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE username = ?1")
    User getUserByName(String username);
    @Modifying
    @Query(value = "UPDATE User SET description=?4, password=?3, username=?2 WHERE Id=?1")
    void updateUser(Long Id, String username, String password, String description);
}
