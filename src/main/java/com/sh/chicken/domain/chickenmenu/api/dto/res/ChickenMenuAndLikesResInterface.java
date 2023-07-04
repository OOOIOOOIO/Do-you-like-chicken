package com.sh.chicken.domain.chickenmenu.api.dto.res;


import com.fasterxml.jackson.annotation.JsonProperty;

public interface ChickenMenuAndLikesResInterface {

    /**
     * Projection 할 때는 db 결과값과 같게 매핑해줘야 함
     * native 쿼리에서 as로 카멜케이스로 변환함
     */

    Long getMenuId();
    String getMenuName();
    String getBrandName();
    String getImg();
    Integer getPrice();
    String getContents();
    Integer getLikes();
}
