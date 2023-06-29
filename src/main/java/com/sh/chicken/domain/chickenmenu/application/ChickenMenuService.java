package com.sh.chicken.domain.chickenmenu.application;

import com.sh.chicken.domain.chickenmenu.api.dto.ChickenMenuAndLikesDto;
import com.sh.chicken.domain.chickenmenu.api.dto.ChickenMenuAndLikesInterface;
import com.sh.chicken.domain.chickenmenu.api.dto.ChickenMenuInfoResDto;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.global.aop.log.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChickenMenuService {

    private final ChickenMenuRepository chickenMenuRepository;

    public ChickenMenuInfoResDto getMenuInfo(long menuId) {

        ChickenMenu chickenMenu = chickenMenuRepository.findById(menuId).orElseThrow(() -> new RuntimeException("메뉴 없음"));
        return new ChickenMenuInfoResDto(chickenMenu);

    }

    // fetch join + 메모리
    @LogTrace
    public void fetchJoinTest(){
        List<ChickenMenu> chickenMenuByFetchJoin = chickenMenuRepository.findChickenMenuByFetchJoin();
        List<ChickenMenuAndLikesDto> chickenMenuAndLikesDtoList = new ArrayList<>();

        for (ChickenMenu chickenMenu : chickenMenuByFetchJoin) {
            ChickenMenuAndLikesDto chickenMenuAndLikesDto = new ChickenMenuAndLikesDto(
                    chickenMenu.getMenuId(),
                    chickenMenu.getMenuName(),
                    chickenMenu.getChickenBrand().getBrandName(),
                    chickenMenu.getImg(),
                    chickenMenu.getPrice(),
                    chickenMenu.getContents(),
                    chickenMenu.getChickenLikeList().size()
            );

            chickenMenuAndLikesDtoList.add(chickenMenuAndLikesDto);
        }

    }

    // select 절 서브쿼리
    public List<ChickenMenuAndLikesInterface> selectSubQueryTest(){
        List<ChickenMenuAndLikesInterface> chickenMenuBySelectSubQuery = chickenMenuRepository.findChickenMenuBySelectSubQuery();

        return chickenMenuBySelectSubQuery;
    }

    // join + from 절 서브쿼리(그룹바이)
    public List<ChickenMenuAndLikesInterface> joinGroupByQueryTest(){
        List<ChickenMenuAndLikesInterface> chickenMenuBySelectSubQuery = chickenMenuRepository.findChickenMenuByFromSubQuery();

        return chickenMenuBySelectSubQuery;
    }
}
