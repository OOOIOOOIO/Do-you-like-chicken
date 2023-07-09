package com.sh.chicken.domain.test;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;

    private String teamName;

    @OneToMany(mappedBy = "team", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Member> memberList = new ArrayList<>();

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public void addMembers(Member member){
        if (member.getTeam() != null) {
            member.getTeam().getMemberList().remove(member);
        }
        memberList.add(member);
        member.setTeam(this);
    }
}
