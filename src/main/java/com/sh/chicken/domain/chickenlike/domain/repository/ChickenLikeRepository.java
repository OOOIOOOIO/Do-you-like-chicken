package com.sh.chicken.domain.chickenlike.domain.repository;

import com.sh.chicken.domain.chickenlike.domain.ChickenLike;
import com.sh.chicken.domain.common.dto.ChickenMenuAndLikesResInterface;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.user.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChickenLikeRepository extends JpaRepository<ChickenLike, Long> {
    Optional<ChickenLike> findByUsers_UserIdAndChickenMenu_MenuId(@Param("userId") Long userId, @Param("menuId") Long menuId);

    Optional<ChickenLike> findByUsersAndChickenMenu(Users users, ChickenMenu chickenMenu);


    /**
     * 마이페이지인데..흠 유저에 들어가야 하나 아님 menu에 들어가야하나 고민해보기
     * @param userId
     * @return
     */
    @Query(value = " SELECT cm.menu_id as menuId, cm.menu_name as menuName, cm.brand_name as brandName, cm.img, cm.price, cm.contents," +
            " (SELECT COUNT(*) FROM chicken_like WHERE cl.menu_id = cm.menu_id) AS likes" +
            " FROM chicken_like cl" +
            " INNER JOIN chicken_menu cm ON cl.menu_id = cm.menu_id" +
            " WHERE cl.user_id = :userId", nativeQuery = true)
    List<ChickenMenuAndLikesResInterface> getChickenMenusInfo(@Param("userId") long userId);

}