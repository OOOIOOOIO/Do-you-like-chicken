package com.sh.chicken.domain.chickenlike.application;

import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ChickenLikeApiService {

    private final ChickenLikeRepository chickenLikeRepository;
    public String addOrDelete(Long menuId, Long userId) {

        // 있는지 확인
        // 없으면 save
        // 있으면 delete

        return null;
    }

}
