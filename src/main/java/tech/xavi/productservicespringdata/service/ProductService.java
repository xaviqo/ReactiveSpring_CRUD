package tech.xavi.productservicespringdata.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.xavi.productservicespringdata.dto.ProductDto;
import tech.xavi.productservicespringdata.repository.ProductRepository;
import tech.xavi.productservicespringdata.util.EntityDtoUtil;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository repository;

    public Flux<ProductDto> getAllProducts(){
        return repository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> getProductById(String id){
        return repository.findById(id)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> insertProduct(Mono<ProductDto> dto){
        return dto.map(EntityDtoUtil::toEntity)
                .flatMap(repository::insert)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> dto){
        return repository.findById(id)
                .flatMap( p -> dto.map(EntityDtoUtil::toEntity)
                        .doOnNext( e -> e.setId(id)))
                .flatMap(repository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Flux<ProductDto> priceRange(int min, int max){
        return repository.findByPriceBetween(min,max)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> deleteProduct(String id){
        return repository.deleteById(id);
    }
}
