package rentaltech.electronics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import rentaltech.electronics.constant.Role;
import rentaltech.electronics.dto.MemberDto;

/**
 * 회원 Entity
 */

@Getter
@Setter
@Entity
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long member_id;   // 아이디

    private String pw;    // 비밀번호

    @Column(name = "member_name")
    private String name;    // 이름

    private String phone;   // 휴대전화

    private String address; // 주소

    private String mail;    // 이메일 주소

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member toMember(MemberDto memberDto) {
        Member member = new Member();
        member.setMail(memberDto.getMail());
        member.setPw(memberDto.getPw());
        member.setName(memberDto.getName());
        member.setPhone(memberDto.getPhone());
        member.setAddress(memberDto.getAddress());
        member.setRole(memberDto.getRole());

        return member;
    }
}
