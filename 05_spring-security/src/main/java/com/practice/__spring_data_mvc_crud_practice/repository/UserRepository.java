package com.practice.__spring_data_mvc_crud_practice.repository;

import com.practice.__spring_data_mvc_crud_practice.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
 * Finds users whose name contains the specified name keyword or whose email contains the specified email keyword and returns them as a paginated result.
 *
 * @param nameKeyword  substring to match against the user's name (case sensitivity depends on the database/collation)
 * @param emailKeyword substring to match against the user's email (case sensitivity depends on the database/collation)
 * @param pageable     pagination and sorting information for the query
 * @return             a Page of User entities where the name contains {@code nameKeyword} or the email contains {@code emailKeyword}
 */
Page<User> findByNameContainingOrEmailContaining(String nameKeyword, String emailKeyword, Pageable pageable);

    /**
 * Checks whether a user with the specified email exists.
 *
 * @param email the email address to check for existence
 * @return {@code true} if a User with the given email exists, {@code false} otherwise
 */
boolean existsByEmail(String email);

    /**
 * Fetches the user with the specified email.
 *
 * @param email the email address to search for
 * @return the User with the given email, or {@code null} if no matching user is found
 */
User findByEmail(String email);
}
