package com.sh.chicken.menu.repository;

import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeRepository;
import com.sh.chicken.domain.chickenmenu.api.dto.ChickenMenuAndLikesDto;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResInterface;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.domain.user.domain.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
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
    ChickenLikeRepository chickenLikeRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ChickenMenuRepository chickenMenuRepository;


    @Test
//    @Disabled
    @DisplayName("insert like")
    public void likeBulkInsertTest(){
//        for(int i = 1; i <= 6; i++){
        for(int i = 6001; i <= 8000; i++){
            String username = "test" + i;
            log.info("=== username ===" + username);
            Users user = usersRepository.findByUsername(username).get();

            for(long j = 1; j <= 67; j+=2){
                ChickenMenu chickenMenu = chickenMenuRepository.findById(j).get();
                ChickenLike chickenLike = ChickenLike.createChickenLike(user, chickenMenu);
                chickenLikeRepository.save(chickenLike);
            }
        }


    }

    @Test
    @DisplayName("jpal fetch join & memory에서 like count 계산")
    @Transactional
    public void menuAndLikeFetchJoinTest(){
        // given
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
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
        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();

        // liked row 개수 약 500개 : 실행시간 = 281ms
        // liked row 개수 약 263169개, 유저 5000명 : 실행 메서드: menuAndLikeFetchJoinTest, 실행시간 = 6821ms
        log.info("실행 메서드: {}, 실행시간 = {}ms", "menuAndLikeFetchJoinTest", totalTimeMillis);

    }

    @Test
    @DisplayName("select절 subquery")
    public void selectSubQueryTest(){
        // given
        StopWatch stopWatch = new StopWatch();

        // when
        stopWatch.start();
        List<ChickenMenuAndLikesResInterface> chickenMenuBySelectSubQuery = chickenMenuRepository.findChickenMenuBySelectSubQuery();
        stopWatch.stop();


        // then
        long totalTimeMillis = stopWatch.getTotalTimeMillis();

        // liked row 개수 약 500개 | 실행시간 = 139ms
        // liked row 개수 약 263169개, 유저 5000명 | 실행 메서드: selectSubQueryTest, 실행시간 = 253ms
        // liked row 개수 약 565169, 유저 10000명 | 실행 메서드: selectSubQueryTest, 실행시간 = 467ms
        log.info("실행 메서드: {}, 실행시간 = {}ms", "selectSubQueryTest", totalTimeMillis);

//        printDto(chickenMenuBySelectSubQuery);

    }

    @Test
    @DisplayName("from절 group by")
    public void joinGroupByQueryTest(){
        // given
        StopWatch stopWatch = new StopWatch();

        // when
        stopWatch.start();
        List<ChickenMenuAndLikesResInterface> chickenMenuByFromSubQuery = chickenMenuRepository.findChickenMenuByFromSubQuery();
        stopWatch.stop();

        // then
        long totalTimeMillis = stopWatch.getTotalTimeMillis();

        // liked row 개수 약 500개 | 실행시간 = 173ms
        // liked row 개수 약 263169개, 유저 5000명 | 실행 메서드: joinGroupByQueryTest, 실행시간 = 206ms
        // liked row 개수 약 565169, 유저 10000명 |
        log.info("실행 메서드: {}, 실행시간 = {}ms", "joinGroupByQueryTest", totalTimeMillis);

//        printDto(chickenMenuByFromSubQuery);
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