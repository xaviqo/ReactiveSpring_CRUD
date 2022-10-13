package tech.xavi.userservicespringdata.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.xavi.userservicespringdata.dto.UserDto;
import tech.xavi.userservicespringdata.service.UserService;

@AllArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping("all")
    public Flux<UserDto> all(){
        return userService.all();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<UserDto>> getUserById(@PathVariable int id){
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<UserDto> saveUser(@RequestBody Mono<UserDto> userDtoMono){
        return  userService.saveUser(userDtoMono);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<UserDto>> updateUser(@PathVariable int id,@RequestBody Mono<UserDto> userDtoMono){
        return userService.updateUser(id,userDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteUSer(@PathVariable int id){
        return userService.deleteUser(id);
    }
}
