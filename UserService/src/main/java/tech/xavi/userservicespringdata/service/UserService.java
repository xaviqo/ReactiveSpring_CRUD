package tech.xavi.userservicespringdata.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.xavi.userservicespringdata.dto.UserDto;
import tech.xavi.userservicespringdata.entity.User;
import tech.xavi.userservicespringdata.repository.UserRepository;
import tech.xavi.userservicespringdata.util.EntityDtoUtil;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Flux<UserDto> all(){
        return userRepository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> getUserById(final int userId){
        return userRepository.findById(userId)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> saveUser(Mono<UserDto> userDto){
        return userDto
                .map(EntityDtoUtil::toEntity)
                .flatMap(userRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> updateUser(final int id, Mono<UserDto> userDto){
        return userRepository.findById(id)
                .flatMap(user -> userDto //to check if user exists
                        .map(EntityDtoUtil::toEntity)
                        .doOnNext(entity -> entity.setId(id)))
                .flatMap(userRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> deleteUser(final int id){
        return userRepository.deleteById(id);
    }


}
