package tech.xavi.userservicespringdata.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import tech.xavi.userservicespringdata.dto.TransactionRequestDto;
import tech.xavi.userservicespringdata.dto.TransactionResponseDto;
import tech.xavi.userservicespringdata.service.TransactionService;

@AllArgsConstructor
@RestController
@RequestMapping("transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public Mono<TransactionResponseDto> createTransaction(@RequestBody Mono<TransactionRequestDto> requestDtoMono){
        return requestDtoMono.flatMap(transactionService::createTransaction);
    }
}
