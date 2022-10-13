package tech.xavi.userservicespringdata.util;

import org.springframework.beans.BeanUtils;
import tech.xavi.userservicespringdata.dto.TransactionRequestDto;
import tech.xavi.userservicespringdata.dto.TransactionResponseDto;
import tech.xavi.userservicespringdata.dto.TransactionStatus;
import tech.xavi.userservicespringdata.dto.UserDto;
import tech.xavi.userservicespringdata.entity.User;
import tech.xavi.userservicespringdata.entity.UserTransaction;

import java.time.LocalDateTime;

public class EntityDtoUtil {

    public static UserDto toDto(User user){
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user,dto);
        return dto;
    }

    public static User toEntity(UserDto dto){
        User user = new User();
        BeanUtils.copyProperties(dto,user);
        return user;
    }

    public static UserTransaction toEntity(TransactionRequestDto dto){
        UserTransaction userTransaction = new UserTransaction();
        userTransaction.setUserId(dto.getUserId());
        userTransaction.setAmount(dto.getAmount());
        userTransaction.setTransactionDate(LocalDateTime.now());
        return userTransaction;
    }

    public static TransactionResponseDto toDto(TransactionRequestDto requestDto, TransactionStatus status){
        TransactionResponseDto responseDto = new TransactionResponseDto();
        responseDto.setAmount(requestDto.getAmount());
        responseDto.setUserId(requestDto.getUserId());
        responseDto.setStatus(status);
        return responseDto;
    }
}
