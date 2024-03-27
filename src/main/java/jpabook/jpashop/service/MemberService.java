package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)     // jpa가 조회하는 동작 시 성능 최적화, 조회 메서드가 많을 경우 묶기 위해
@Service
public class MemberService {

    private MemberRepository memberRepository;

     // setter injection => Test Code 작성 시 Mock(가짜,임의의) 주입 가능
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /* 회원 가입 */
    @Transactional  // (readOnly = false)
    public Long join(Member member) {
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());   // 멀티쓰레드 상황 고려 멤버네임을 유니크로 설정
        if (!findMembers.isEmpty()) {
           throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }


    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}
