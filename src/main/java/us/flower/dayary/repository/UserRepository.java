package us.flower.dayary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import us.flower.dayary.domain.User;

/**
 * Created by KimYJ on 2017-07-12.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
