package com.sh.chicken.global.util.redis.pubsub;

import com.sh.chicken.domain.Coupon.domain.repository.CouponRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisPubSubService {

    private final RedisMessageListenerContainer container;
    private final RedisPublisher redisPublisher;
    private final RedisSubscriber redisSubscriber;
    private final ChannelTopic topic;
    private final CouponRepositoryCustom couponRepository;

    public void subscribe(){

        container.addMessageListener(redisSubscriber, topic); // 토픽 등록
        // topic을 구독한 subscriber db에 저장

    }

    public void publish(String message){
        redisPublisher.publish(topic, message);

    }
}
