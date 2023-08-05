package com.sh.chicken.domain.common.dto;


import java.io.Serializable;

public interface ChickenMenuAndLikesResInterface {
    Long getMenuId();
    String getMenuName();
    String getBrandName();
    String getImg();
    Integer getPrice();
    String getContents();
    Integer getLikes();
}
