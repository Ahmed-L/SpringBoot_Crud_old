package com.example.blog.service;

import com.example.blog.model.BlogPost;
import com.example.blog.model.Role;
import com.example.blog.model.User;
import com.example.blog.repository.RoleRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{
    private final UserRepository userRepository;
    private RoleRepository roleRepository;
    private EntityManager entityManager;
//    private PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository, RoleRepository roleRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
//        this.passwordEncoder= passwordEncoder;
        this. roleRepository = roleRepository;
        this.entityManager=entityManager;
    }
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public User updateUserById(Long id, User user) {
        Optional<User> optionalUser =  userRepository.findById(id).map(element -> {
            if(user.getEmail()!=null)
                element.setEmail(user.getEmail());
            if(user.getName()!=null)
                element.setName(user.getName());
//            if(user.getRoles()!=null)
//                element.setRoles(user.getRoles());

            return userRepository.save(element);
        });
        return optionalUser.get();
    }
    @Override
    public User createNewUser(User user) {
        User toBeCreatedUser = new User();
        toBeCreatedUser.setName(user.getName());
        toBeCreatedUser.setEmail(user.getEmail());
//        Role role = roleRepository.findByName("ROLE_ADMIN");
//        if(role == null){
//            role = checkRoleExist();
//        }
//        toBeCreatedUser.setRoles(Arrays.asList(role));
        // set pasword
//        toBeCreatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        toBeCreatedUser.setPassword(user.getPassword());
        return userRepository.save(toBeCreatedUser);
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public String deleteUserById(Long id) {
        return null;
    }

    public Page<User> getUsersWithPagination(int offset, int pageSize, String field)
    {
        Page<User> users = userRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return users;
    }
    public List<User> findUsersWithBlogPosts()
    {
        List<User> ret = userRepository.findUsersWithBlogPosts();
        System.out.println(ret.get(0));
        return ret;
    }

    public List<User> findUsersWithBlogPostsAndFetchPosts() {
        String jpql = "SELECT u FROM User u LEFT JOIN FETCH u.blogPosts";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        List<User> ret = query.getResultList();
        return ret;
    }
}
