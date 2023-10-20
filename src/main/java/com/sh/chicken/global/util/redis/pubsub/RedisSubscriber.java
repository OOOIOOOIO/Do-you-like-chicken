package com.sh.chicken.global.util.redis.pubsub;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class RedisSubscriber implements MessageListener {
    private final RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper objectMapper;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // simple message 테스트
        // 여기서 api를 불러야되는구나
        try {
            String msg = (String) redisTemplate.getStringSerializer()
                    .deserialize(message.getBody());

            String data = objectMapper.readValue(msg, String.class);
            log.info(data);
        } catch (Exception e){
            log.error(e.getMessage());
        }


        // pojo 메시지 테스트
//        Member member = objectMapper.readValue(message.getBody(), Member.class);
//        System.out.println(member);
//        System.out.println(member.getFName());
//        System.out.println(member.getLName());
        log.info("Message received : " + new String(message.getBody()));
    }



}
