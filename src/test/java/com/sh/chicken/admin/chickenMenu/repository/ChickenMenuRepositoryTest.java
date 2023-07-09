package com.sh.chicken.admin.chickenMenu.repository;

import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeRepository;
import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResInterface;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.domain.user.domain.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

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
    public void getAllChickenMenusWithLike(){
        // given
        StopWatch stopWatch = new StopWatch();

        // when
        stopWatch.start();
        List<ChickenMenuAndLikesResInterface> chickenMenuBySelectSubQuery = chickenMenuRepository.getAllChickenMenusWithLike();
        stopWatch.stop();


        // then
        long totalTimeMillis = stopWatch.getTotalTimeMillis();

        // 실행시간 : 139ms
        log.info("실행 메서드: {}, 실행시간 = {}ms", "selectSubQueryTest", totalTimeMillis);

        printDto(chickenMenuBySelectSubQuery);

    }


    @Test
    public void getAllChickenMenusWithLikeOrderByLikesDESC(){
        // given
        StopWatch stopWatch = new StopWatch();

        // when
        stopWatch.start();
        List<ChickenMenuAndLikesResInterface> chickenMenuBySelectSubQuery = chickenMenuRepository.getAllChickenMenusWithLikeOrderByLikesDESC();
        stopWatch.stop();


        // then
        long totalTimeMillis = stopWatch.getTotalTimeMillis();

        // 실행시간 : 139ms
        log.info("실행 메서드: {}, 실행시간 = {}ms", "selectSubQueryTest", totalTimeMillis);

        printDto(chickenMenuBySelectSubQuery);

    }

    private void printDto(List<ChickenMenuAndLikesResInterface> chickenMenuBySelectSubQuery){
        for (ChickenMenuAndLikesResInterface chickenMenuAndLikesInterface : chickenMenuBySelectSubQuery) {
            log.info(chickenMenuAndLikesInterface.getMenuId()+"");
            log.info(chickenMenuAndLikesInterface.getMenuName());
            log.info(chickenMenuAndLikesInterface.getBrandName());
            log.info(chickenMenuAndLikesInterface.getPrice()+"");
            log.info(chickenMenuAndLikesInterface.getImg());
            log.info(chickenMenuAndLikesInterface.getContents());
            log.info(chickenMenuAndLikesInterface.getLikes()+"");
            log.info("========================================================");
        }
    }
    
    
    
}
