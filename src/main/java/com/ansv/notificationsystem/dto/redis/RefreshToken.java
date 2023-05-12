package com.ansv.notificationsystem.dto.redis;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Getter
@Setter
@RedisHash(value = "refreshToken")
public class RefreshToken {

//    @Id
    private String id;

    @NonNull
    private String refreshToken;

    @NonNull
    private String username;

    private String department;

    private String position;

    @NonNull
    private String uuid;

    @TimeToLive(unit = TimeUnit.SECONDS)
    @NonNull
    private Date expiredTime;

    @NonNull
    private String serviceName;

}
