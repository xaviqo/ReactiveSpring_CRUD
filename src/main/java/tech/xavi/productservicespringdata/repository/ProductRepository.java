package tech.xavi.productservicespringdata.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.xavi.productservicespringdata.entity.Product;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product,String> {
    Flux<Product> findByPriceBetween(int priceGT, int priceLT);
}
