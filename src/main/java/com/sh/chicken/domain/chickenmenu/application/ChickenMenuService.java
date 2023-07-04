package com.sh.chicken.domain.chickenmenu.application;

import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuAndLikesResInterface;
import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuInfoResDto;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.global.aop.log.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ChickenMenuService {

    private final ChickenMenuRepository chickenMenuRepository;

    public ChickenMenuAndLikesResInterface getMenuInfo(long menuId) {

        ChickenMenuAndLikesResInterface chickenMenu = chickenMenuRepository.findMenuAndLikesByMenuId(menuId).orElseThrow(() -> new RuntimeException("메뉴 없음"));
        return chickenMenu;

    }



    // select 절 서브쿼리
    @LogTrace
    public List<ChickenMenuAndLikesResInterface> selectSubQueryTest(){
        List<ChickenMenuAndLikesResInterface> chickenMenuBySelectSubQuery = chickenMenuRepository.getAllChickenMenusWithLike();

        return chickenMenuBySelectSubQuery;
    }


    /**


    // fetch join + 메모리
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
    // join + from 절 서브쿼리(그룹바이)
    public List<ChickenMenuAndLikesInterface> joinGroupByQueryTest(){
        List<ChickenMenuAndLikesInterface> chickenMenuBySelectSubQuery = chickenMenuRepository.findChickenMenuByFromSubQuery();

        return chickenMenuBySelectSubQuery;
    }


     */
}
