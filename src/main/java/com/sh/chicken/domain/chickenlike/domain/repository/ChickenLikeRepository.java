package com.sh.chicken.domain.chickenlike.domain.repository;

import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.user.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChickenLikeRepository extends JpaRepository<ChickenLike, Long> {
    Optional<ChickenLike> findByUsers_UserIdAndChickenMenu_MenuId(@Param("userId") Long userId, @Param("menuId") Long menuId);

    public Optional<ChickenLike> findByUsersAndChickenMenu(Users users, ChickenMenu chickenMenu);
}
