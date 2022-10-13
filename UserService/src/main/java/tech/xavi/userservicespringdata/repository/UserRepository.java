package tech.xavi.userservicespringdata.repository;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import tech.xavi.userservicespringdata.entity.User;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User,Integer> {

    @Modifying
    @Query("UPDATE users " +
            "SET balance = balance - :amount " +
            "WHERE id = :userId " +
            "AND balance >= :amount")
    Mono<Boolean> updateUserBalance(int userId, int amount);
}
