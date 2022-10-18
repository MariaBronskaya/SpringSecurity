package web.SpringSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.SpringSecurity.model.Role;
import web.SpringSecurity.model.User;
import web.SpringSecurity.repositories.UsersRepository;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository repository;


    @Autowired
    public UserServiceImpl(UsersRepository repository) {
        this.repository = repository;
    }


    @Override

    public List<User> getAllUsers() { // listUsers
        return repository.findAll();
    }

    @Override

    public User getUserById(Long id) {
        return repository.findById(id).get();
    }

    @Transactional // добавила
    @Override
    public void saveUser(User user) {
        repository.save(user);
    }

    @Override
    public void createNewUser(User user) {
        user.setPassword(PasswordEncoder().encode(user.getPassword()));
        user.addRole(Role.USER);
        repository.save(user);

    }

    @Override
    public void makeAdmin(User user) {
        user.addRole(Role.ADMIN);
        repository.save(user);
    }


    @Transactional // добавила
    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    @Transactional // добавила
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
