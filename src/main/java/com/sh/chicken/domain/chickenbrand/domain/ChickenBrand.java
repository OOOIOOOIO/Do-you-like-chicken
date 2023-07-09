package com.sh.chicken.domain.chickenbrand.domain;


import com.sh.chicken.admin.controller.dto.ChickenBrandUploadDto;
import com.sh.chicken.domain.chickenmenu.domain.ChickenMenu;
import com.sh.chicken.domain.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ChickenBrand extends BaseTimeEntity implements Persistable<String> {

    @Id
    private String brandName;

    @OneToMany(mappedBy = "chickenBrand", cascade ={CascadeType.REMOVE, CascadeType.PERSIST})
    private List<ChickenMenu> chickenMenuList = new ArrayList<>();

    @Builder
    private ChickenBrand(String brandName) {
        this.brandName = brandName;
    }

    /**
     * 생성 메서드
     * @param chickenBrandUploadDto
     * @return
     */
    public static ChickenBrand createChickenBrand(ChickenBrandUploadDto chickenBrandUploadDto){
        return ChickenBrand.builder()
                .brandName(chickenBrandUploadDto.getBrandName())
                .build();
    }

    /**
     * 양방향 연관관계
     * @param chickenMenu
     */
    public void addChickenMenu(ChickenMenu chickenMenu){
        if (chickenMenu.getChickenBrand() != null) {
            chickenMenu.getChickenBrand().getChickenMenuList().remove(chickenMenu);
        }
        this.chickenMenuList.add(chickenMenu);
        chickenMenu.setChickenBrand(this);

    }

    @Override
    public String getId() {
        return brandName;
    }

    @Override
    public boolean isNew() {
        return getCreatedAt() == null;
    }
}
