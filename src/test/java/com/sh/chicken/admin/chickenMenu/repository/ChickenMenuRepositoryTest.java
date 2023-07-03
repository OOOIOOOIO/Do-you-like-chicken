package com.sh.chicken.admin.chickenMenu.repository;

import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeRepository;
import com.sh.chicken.domain.chickenmenu.api.dto.ChickenMenuAndLikesDto;
import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuAndLikesResInterface;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.domain.user.domain.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class ChickenMenuRepositoryTest {

    @Autowired
    ChickenLikeRepository chickenLikeRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ChickenMenuRepository chickenMenuRepository;


    @Test
    public void bulkInsertTest(){
        for(int i = 1; i <= 4; i++){
            String username = "test" + i;
            log.info("=== username ===" + username);
            Users user = usersRepository.findByUsername(username).get();

            for(long j = 1; j <= 67; j++){
                ChickenMenu chickenMenu = chickenMenuRepository.findById(j).get();
                ChickenLike chickenLike = ChickenLike.createChickenLike(user, chickenMenu);
                chickenLikeRepository.save(chickenLike);
            }
        }

    }

    @Test
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

        // 실행시간 281ms
        log.info("실행 메서드: {}, 실행시간 = {}ms", "menuAndLikeFetchJoinTest", totalTimeMillis);

    }
    
    @Test
    public void selectSubQueryTest(){
        // given
        StopWatch stopWatch = new StopWatch();

        // when
        stopWatch.start();
        List<ChickenMenuAndLikesResInterface> chickenMenuBySelectSubQuery = chickenMenuRepository.findChickenMenuBySelectSubQuery();
        stopWatch.stop();


        // then
        long totalTimeMillis = stopWatch.getTotalTimeMillis();

        // 실행시간 : 139ms
        log.info("실행 메서드: {}, 실행시간 = {}ms", "selectSubQueryTest", totalTimeMillis);

//        printDto(chickenMenuBySelectSubQuery);

    }
    
    @Test
    public void joinGroupByQueryTest(){
        // given
        StopWatch stopWatch = new StopWatch();

        // when
        stopWatch.start();
        List<ChickenMenuAndLikesResInterface> chickenMenuBySelectSubQuery = chickenMenuRepository.findChickenMenuByFromSubQuery();
        stopWatch.stop();

        // then
        long totalTimeMillis = stopWatch.getTotalTimeMillis();

        // 173ms
        log.info("실행 메서드: {}, 실행시간 = {}ms", "joinGroupByQueryTest", totalTimeMillis);

//        printDto(chickenMenuBySelectSubQuery);
    }

    private void printDto(List<ChickenMenuAndLikesResInterface> chickenMenuBySelectSubQuery){
        for (ChickenMenuAndLikesResInterface chickenMenuAndLikesInterface : chickenMenuBySelectSubQuery) {
            System.out.println(chickenMenuAndLikesInterface.getMenu_id());
            System.out.println(chickenMenuAndLikesInterface.getMenu_name());
            System.out.println(chickenMenuAndLikesInterface.getBrand_name());
            System.out.println(chickenMenuAndLikesInterface.getPrice());
            System.out.println(chickenMenuAndLikesInterface.getImg());
            System.out.println(chickenMenuAndLikesInterface.getContents());
            System.out.println(chickenMenuAndLikesInterface.getLikes());
            System.out.println("========================================================");
        }
    }
    
    
    
}
