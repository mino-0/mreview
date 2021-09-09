package org.mino.mreview.repository;

import org.junit.jupiter.api.Test;
import org.mino.mreview.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMember() {
        IntStream.rangeClosed(1,100).forEach(i->{
            Member member = Member.builder()
                    .email("r"+i+"mino.org")
                    .pw("1111")
                    .nickname("reviewer"+i).build();
            memberRepository.save(member);
        });
    }
    @Commit
    @Transactional
    @Test
    public void testDeleteMember() {
        Long mid = 1L;

        Member member = Member.builder().mid(mid).build();

//        memberRepository.deleteById(mid);
//        reviewRepository.deleteByMember(member);
        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);
    }
}
