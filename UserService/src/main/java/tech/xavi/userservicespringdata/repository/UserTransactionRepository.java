package tech.xavi.userservicespringdata.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import tech.xavi.userservicespringdata.entity.UserTransaction;

@Repository
public interface UserTransactionRepository extends ReactiveCrudRepository<UserTransaction,Integer> {
}
