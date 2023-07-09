package com.sh.chicken.domain.test;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    private String memberName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String memberName, Team team) {
        this.memberName = memberName;
        this.team = team;
    }

    public Member(String memberName) {
        this.memberName = memberName;
    }

    public void setTeam(Team team){
        this.team = team;
    }
}
