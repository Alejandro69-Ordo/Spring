package com.example.demo.controladores;

import com.example.demo.Exceptions.NotFoundException;
import com.example.demo.Exceptions.RequestException;
import com.example.demo.Exceptions.Respuesta;
import com.example.demo.models.entity.User;
import com.example.demo.models.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user){

       Optional<User> usuario= StreamSupport.stream(userService.findAll().spliterator(),false)
                .filter(s->user.getEmail().equals(s.getEmail())).findFirst();
       if(usuario.isPresent()){
           throw new RequestException("U-001","Email ya existente");
       }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> read(@PathVariable Long userId){
        Optional<User> user=userService.findById(userId);
        if(!user.isPresent()){
            throw new NotFoundException(userId+"","U-000",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id")Long userId,@RequestBody User user_details){
        Optional<User> userOptional=userService.findById(userId);
        if(!userOptional.isPresent()){
            throw new NotFoundException(userId+"","U-000",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<User> usuario_validation = null;
        if(!user_details.getEmail().equals(userOptional.get().getEmail())){
            usuario_validation= StreamSupport.stream(userService.findAll().spliterator(),false)
                    .filter(s->user_details.getEmail().equals(s.getEmail())).
                    findFirst();
        }

        if(usuario_validation!=null){
            if(usuario_validation.isPresent()){
                throw new RequestException("U-001","Email ya existente");
            }
        }

        userOptional.get().setNombre(user_details.getNombre());
        userOptional.get().setEmail(user_details.getEmail());
        userOptional.get().setEstado(user_details.getEstado());
        return  ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userOptional.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long userId){
        Optional<User> user=userService.findById(userId);

        if(!user.isPresent()){
            throw new NotFoundException(userId+"","U-000",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userService.deleteById(userId);
        return  new ResponseEntity<>(new Respuesta("Usuario Eliminado"),HttpStatus.CREATED);
    }

    @GetMapping
    public List<User> readAll(){
      return StreamSupport.stream(userService.findAll().spliterator(),false)
              .collect(Collectors.toList());
    }

}
