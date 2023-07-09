package com.sh.chicken.domain.chickenLike.service;

import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.chickenlike.domain.repository.ChickenLikeRepository;
import com.sh.chicken.domain.chickenmenu.api.dto.ChickenMenuAndLikesDto;
import com.sh.chicken.domain.chickenmenu.application.ChickenMenuService;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.domain.user.domain.Users;
import com.sh.chicken.domain.user.domain.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
@Transactional
public class ChickenLikeServiceTest {

    @Autowired
    ChickenMenuService chickenMenuService;







}
