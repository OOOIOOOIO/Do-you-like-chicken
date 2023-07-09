package com.sh.chicken.cascadetest;

import com.sh.chicken.domain.test.Member;
import com.sh.chicken.domain.test.MemberRepository;
import com.sh.chicken.domain.test.Team;
import com.sh.chicken.domain.test.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class CascadeTest {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void oneToManyListTest(){
        // given
        Team team = teamRepository.findById(1L).get();
        // when
        assertThat(team.getTeamName()).isEqualTo("팀1");
        List<Member> memberList = team.getMemberList();

        for (Member member : memberList) {
            log.info("==="+member.getMemberId());

        }

        // then

    }

    @Test
    public void oneToManySaveTest(){
        // given
        Team team = new Team("팀팀123");
        Member 멤1 = new Member("멤123");
        Member 멤2 = new Member("멤234");
        Member 멤3 = new Member("멤3345");

        team.addMembers(멤1);
        team.addMembers(멤2);
        team.addMembers(멤3);
//        Member 멤1 = new Member("멤1", saveTeam);
//        Member 멤2 = new Member("멤2", saveTeam);
//        Member 멤3 = new Member("멤3", saveTeam);

        // when

        Team saveTeam = teamRepository.save(team);

    }

    @Test
    public void oneToManyDeleteTest(){
        // given
        teamRepository.deleteById(9L);

        // when

        // then

    }


}
