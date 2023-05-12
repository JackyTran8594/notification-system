package com.ansv.notificationsystem.dto.redis;

//import com.redis.om.spring.annotations.Indexed;
//import com.redis.om.spring.annotations.Searchable;
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
@RedisHash(value = "accessToken")
public class AccessToken {


    private String id;

    private String accessToken;


    @NonNull
    private String username;


    private String department;


    private String position;

//    @Indexed
//    private Set<String> tags = new HashSet<String>();

    @NonNull
    private String uuid;

    @NonNull
    private Date expiredTime;

    @NonNull
    private String serviceName;
}
