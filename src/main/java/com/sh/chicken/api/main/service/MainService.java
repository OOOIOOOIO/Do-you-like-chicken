package com.sh.chicken.api.main.service;

import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuAndLikesResInterface;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.api.common.dto.ChickenMenusAndTotalLikeResListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MainService {

    private final ChickenMenuRepository chickenMenuRepository;

    public ChickenMenusAndTotalLikeResListDto getAllChickenMenus(){
        List<ChickenMenuAndLikesResInterface> chickenMenuBySelectSubQuery = chickenMenuRepository.getAllChickenMenusWithLike();

        return new ChickenMenusAndTotalLikeResListDto(chickenMenuBySelectSubQuery);
    }
}
