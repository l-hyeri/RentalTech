package rentaltech.electronics.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rentaltech.electronics.dto.MemberDto;
import rentaltech.electronics.entity.Member;
import rentaltech.electronics.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(MemberDto memberDto) { // 회원가입
        Member member = Member.toMember(memberDto);

        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    public MemberDto login(MemberDto memberDto) {    // 로그인

        Optional<Member> findMember = memberRepository.findByMail(memberDto.getMail());

        if (findMember.isPresent()) {
            Member member = findMember.get();
            if (member.getPw().equals(memberDto.getPw())) {
                MemberDto dto = memberDto.toMemberDto(member);
                return dto;
            } else {    // 비밀번호 불일치
                return null;
            }
        } else {    // 조회 경로가 없는 경우
            return null;
        }
    }

    @Transactional(readOnly = true)
    public MemberDto findByMail(String mail) {    // 회원정보 수정을 위한 이메일 조회
        Optional<Member> findMember = memberRepository.findByMail(mail);

        if (findMember.isPresent()) {
            Member member = findMember.get();
            MemberDto dto = MemberDto.toMemberDto(member);
            return dto;
        } else {
            return null;
        }
    }

    public void edit(MemberDto memberDto) {
        Optional<Member> findMember = memberRepository.findByMail(memberDto.getMail());

        Member member = findMember.get();
        member.editMember(memberDto);
    }

    // 중복회원 확인 (회원가입)
    public void validateDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findByMail(member.getMail());

        if (findMember.isPresent()) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}