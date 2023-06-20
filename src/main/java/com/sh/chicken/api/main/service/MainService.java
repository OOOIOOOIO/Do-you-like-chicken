package com.sh.chicken.api.main.service;

import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.chickenmenu.domain.repository.ChickenMenuRepository;
import com.sh.chicken.api.main.controller.dto.MainResDto;
import com.sh.chicken.api.main.controller.dto.MainResListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainService {

    private final ChickenMenuRepository chickenMenuRepository;

    public MainResListDto getChickenMenus(){
        List<ChickenMenu> allByImgIsNotNull = chickenMenuRepository.findAllByImgIsNotNullOrderByPriceDesc();

        List<MainResDto> mainResDtoList = allByImgIsNotNull.stream()
                .map(MainResDto::new)
                .collect(Collectors.toList());

        return new MainResListDto(mainResDtoList);
    }
}
