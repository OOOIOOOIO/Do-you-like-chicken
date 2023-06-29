package com.sh.chicken.domain.chickenmenu.api.dto;


import lombok.Getter;

public interface ChickenMenuAndLikesInterface {

    Long getMenu_id();
    String getMenu_name();
    String getBrand_name();
    String getImg();
    Integer getPrice();
    String getContents();

    Integer getLikes();
}
