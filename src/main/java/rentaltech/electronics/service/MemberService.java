package rentaltech.electronics.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rentaltech.electronics.dto.memberDto;
import rentaltech.electronics.entity.Member;
import rentaltech.electronics.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(memberDto memberDto) { // 회원가입
        Member member = Member.toMember(memberDto);

        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    public memberDto login(memberDto memberDto) {    // 로그인

        Optional<Member> findMember = memberRepository.findByMail(memberDto.getMail());

        if (findMember.isPresent()) {
            Member member = findMember.get();
            if (member.getPw().equals(memberDto.getPw())) {
                memberDto dto = memberDto.toMemberDto(member);
                return dto;
            } else {    // 비밀번호 불일치
                return null;
            }
        } else {    // 조회 경로가 없는 경우
            return null;
        }
    }

    // 중복회원 확인 (회원가입)
    public void validateDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findByMail(member.getMail());

        if (findMember.isPresent()) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}