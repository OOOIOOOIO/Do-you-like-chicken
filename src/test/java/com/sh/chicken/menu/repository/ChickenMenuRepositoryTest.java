package com.sh.chicken.menu.repository;

import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResInterface;
import com.sh.chicken.menu.ChickenMenuAndLikesTestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
public class ChickenMenuRepositoryTest {

    @Autowired
    ChickenMenuRepository chickenMenuRepository;


    /**
     * liked row 개수 약 500개 : 실행시간 = 281ms
     * liked row 개수 263169개, 유저 5000명 : 실행 메서드: menuAndLikeFetchJoinTest, 실행시간 = 6821ms
     * liked row 개수 565169개, 유저 10000명 | 실행 메서드: selectSubQueryTest, 실행시간 = time over(12sec 901ms)
     */
    @Test
    @DisplayName("jpql fetch join & memory에서 like count 계산")
    @Transactional
    public void menuAndLikeFetchJoinTest(){
        // given
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        List<ChickenMenu> chickenMenuByFetchJoin = chickenMenuRepository.findChickenMenuByFetchJoin();
        List<ChickenMenuAndLikesTestDto> chickenMenuAndLikesDtoList = new ArrayList<>();

        for (ChickenMenu chickenMenu : chickenMenuByFetchJoin) {
            ChickenMenuAndLikesTestDto chickenMenuAndLikesDto = new ChickenMenuAndLikesTestDto(
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
        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();

        log.info("실행 메서드: {}, 실행시간 = {}ms", "menuAndLikeFetchJoinTest", totalTimeMillis);

    }

    /**
     * liked row 개수 약 500개 | 실행시간 = 139ms
     * liked row 개수 263169개, 유저 5000명 | 실행 메서드: selectSubQueryTest, 실행시간 = 253ms
     * liked row 개수 565169개, 유저 10000명 | 실행 메서드: selectSubQueryTest, 실행시간 = 161 ~198ms
     */
    @Test
    @DisplayName("select절 subquery")
    public void selectSubQueryTest(){
        // given
        StopWatch stopWatch = new StopWatch();

        // when
        stopWatch.start();
        List<ChickenMenuAndLikesResInterface> chickenMenuBySelectSubQuery = chickenMenuRepository.findChickenMenuBySelectSubQuery();
        stopWatch.stop();

        long sum = 0;

        for (ChickenMenuAndLikesResInterface chickenMenuAndLikesResInterface : chickenMenuBySelectSubQuery) {
            sum += chickenMenuAndLikesResInterface.getLikes().intValue();
        }

        // then
        long totalTimeMillis = stopWatch.getTotalTimeMillis();


        log.info("실행 메서드: {}, 실행시간 = {}ms", "selectSubQueryTest", totalTimeMillis);
        log.info("메뉴 개수 : " + chickenMenuBySelectSubQuery.size());
        log.info("좋아요 총개수 : " + sum);

    }

    /**
     * liked row 개수 약 500개 || 실행시간 = 173ms
     * liked row 개수 약 263169개, 유저 5000명 || 실행 메서드: joinGroupByQueryTest, 실행시간 = 206ms
     * liked row 개수 약 565169개, 유저 10000명 || 실행 메서드: joinGroupByQueryTest, 실행시간 = 178 ~ 220ms
     */
    @Test
    @DisplayName("join절 group by")
    public void joinGroupByQueryTest(){
        // given
        StopWatch stopWatch = new StopWatch();

        // when
        stopWatch.start();
        List<ChickenMenuAndLikesResInterface> chickenMenuByFromSubQuery = chickenMenuRepository.findChickenMenuByFromSubQuery();
        stopWatch.stop();
        long sum = 0;

        for (ChickenMenuAndLikesResInterface chickenMenuAndLikesResInterface : chickenMenuByFromSubQuery) {
            sum += chickenMenuAndLikesResInterface.getLikes().intValue();
        }

        // then
        long totalTimeMillis = stopWatch.getTotalTimeMillis();


        log.info("실행 메서드: {}, 실행시간 = {}ms", "joinGroupByQueryTest", totalTimeMillis);
        log.info("메뉴 개수 : " + chickenMenuByFromSubQuery.size());
        log.info("좋아요 총개수 : " + sum);

    }

    private void printDto(List<ChickenMenuAndLikesResInterface> chickenMenuBySelectSubQuery){
        for (ChickenMenuAndLikesResInterface chickenMenuAndLikesInterface : chickenMenuBySelectSubQuery) {
            System.out.println(chickenMenuAndLikesInterface.getMenuId());
            System.out.println(chickenMenuAndLikesInterface.getMenuName());
            System.out.println(chickenMenuAndLikesInterface.getBrandName());
            System.out.println(chickenMenuAndLikesInterface.getPrice());
            System.out.println(chickenMenuAndLikesInterface.getImg());
            System.out.println(chickenMenuAndLikesInterface.getContents());
            System.out.println(chickenMenuAndLikesInterface.getLikes());
            System.out.println("========================================================");
        }

    }



}