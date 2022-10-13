package tech.xavi.userservicespringdata.entity;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class UserTransaction {

    private Integer id;
    private Integer userId;
    private Integer amount;
    private LocalDateTime transactionDate;
}
