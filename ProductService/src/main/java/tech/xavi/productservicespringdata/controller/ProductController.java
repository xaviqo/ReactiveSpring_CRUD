package tech.xavi.productservicespringdata.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.xavi.productservicespringdata.dto.ProductDto;
import tech.xavi.productservicespringdata.entity.Product;
import tech.xavi.productservicespringdata.service.ProductService;

@AllArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService service;

    @GetMapping("range")
    public Flux<ProductDto> range(@RequestParam int min, @RequestParam int max){
        return service.priceRange(min,max);
    }

    @GetMapping("all")
    public Flux<ProductDto> all(){
        return service.getAllProducts();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> getById(@PathVariable String id){
        return service.getProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ProductDto> insert(@RequestBody Mono<ProductDto> productDto){
        return service.insertProduct(productDto);
    }

    @PutMapping("{id}")
    public  Mono<ResponseEntity<ProductDto>> update(@PathVariable String id, @RequestBody Mono<ProductDto> productDto){
        return service.updateProduct(id,productDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable String id){
        return service.deleteProduct(id);
    }
}
