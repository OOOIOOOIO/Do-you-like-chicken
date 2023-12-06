package com.sh.chicken.domain.chickenmenu.domain.repository;

import com.sh.chicken.domain.chickenmenu.api.dto.res.ChickenMenuInfoResDto;

import java.util.List;
import java.util.Optional;

public interface ChickenMenuQueryRepository {

    public List<ChickenMenuInfoResDto> getAllMenusWithTotalLikeLikesDesc();

    public List<ChickenMenuInfoResDto> getAllMenusWithTotalLikePriceDesc();

    public List<ChickenMenuInfoResDto> getAllMenus();

    public Optional<ChickenMenuInfoResDto> getMenuInfo(Long menuId);

    public Long getTotalMenuCount();
}
