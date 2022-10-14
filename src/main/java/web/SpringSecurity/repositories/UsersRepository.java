package web.SpringSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.SpringSecurity.model.User;


@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
