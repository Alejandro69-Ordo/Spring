package com.example.demo.models.services;

import com.example.demo.models.entity.User;
import com.example.demo.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.Optional;

@Service
public class Userlpml implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly=true)
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll((org.springframework.data.domain.Pageable) pageable);
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<User> findById(Long id_usuario) {
        return userRepository.findById(id_usuario);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id_usuario) {
        userRepository.deleteById(id_usuario);
    }
}
