package tech.xavi.userservicespringdata.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tech.xavi.userservicespringdata.dto.TransactionRequestDto;
import tech.xavi.userservicespringdata.dto.TransactionResponseDto;
import tech.xavi.userservicespringdata.dto.TransactionStatus;
import tech.xavi.userservicespringdata.repository.UserRepository;
import tech.xavi.userservicespringdata.repository.UserTransactionRepository;
import tech.xavi.userservicespringdata.util.EntityDtoUtil;

@AllArgsConstructor
@Service
public class TransactionService {

    private final UserRepository userRepository;
    private final UserTransactionRepository transactionRepository;

    public Mono<TransactionResponseDto> createTransaction(final TransactionRequestDto requestDto){
        return userRepository.updateUserBalance(requestDto.getUserId(), requestDto.getAmount())
                .filter(Boolean::booleanValue)
                .map(b -> EntityDtoUtil.toEntity(requestDto))
                .flatMap(transactionRepository::save)
                .map(ut -> EntityDtoUtil.toDto(requestDto,TransactionStatus.APPROVED))
                .defaultIfEmpty(EntityDtoUtil.toDto(requestDto,TransactionStatus.DECLINED));

    }
}

/*
    .map( b -> EntityDtoUtil::toEntity(requestDtoMono))
                .flatMap(transactionRepository::save)
                .map( ut -> EntityDtoUtil::toDto(requestDtoMono, TransactionStatus.APPROVED))
        .defaultIfEmpty(EntityDtoUtil::toDto(requestDtoMono,TransactionStatus.DECLINED));
* */
