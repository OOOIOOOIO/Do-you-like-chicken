package com.sh.chicken.web.chickenmenuinfo.service;

import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.web.chickenmenuinfo.controller.dto.ChickenMenuInfoResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChickenMenuInfoService {

    private final ChickenMenuRepository chickenMenuRepository;

    public ChickenMenuInfoResDto getMenuInfo(long menuId) {

        ChickenMenu chickenMenu = chickenMenuRepository.findById(menuId).orElseThrow(() -> new RuntimeException("메뉴 없음"));

        return new ChickenMenuInfoResDto(chickenMenu);

    }
}
