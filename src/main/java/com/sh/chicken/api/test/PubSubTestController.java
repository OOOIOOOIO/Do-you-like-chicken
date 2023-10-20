package com.sh.chicken.api.test;

import com.sh.chicken.global.util.redis.pubsub.RedisPubSubService;
import com.sh.chicken.global.util.redis.pubsub.RedisPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pubsub")
@RequiredArgsConstructor
@Slf4j
public class PubSubTestController {

    private final RedisPubSubService redisPubSubService;

    //publish
    @PostMapping("/publish/{message}")
    public void publishMessage(@PathVariable String message) {

        // simple message
        //redisPublisher.publish(myTopic, message);

        // pojo
//        Address address = new Address("Korea Seoul", "kangnam");
//        Member member = new Member(null, "kildong", "hong", address)
        log.info("============= PUBLISH CHANNEL =============");
        redisPubSubService.publish(message);
    }

    @PostMapping("/subscribe")
    public void subscribeChannel(){

        redisPubSubService.subscribe();

    }
    //subscribe


}
