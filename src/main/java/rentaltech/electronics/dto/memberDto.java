package rentaltech.electronics.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import rentaltech.electronics.entity.Member;

@Getter
@Setter
public class memberDto {

    @NotNull(message = "이메일 입력은 필수입니다.")
    private String mail;

    @NotNull(message = "비밀번호 입력은 필수입니다.")
    private String pw;

    @NotNull
    private String check_pw;

    @NotNull(message = "이름 입력은 필수입니다.")
    private String name;

    @NotNull(message = "휴대폰 번호 입력은 필수입니다.")
    private String phone;

    @NotNull(message = "주소 입력은 필수입니다.")
    private String address;

    public static memberDto toMemberDto(Member member) {
        memberDto memberDto = new memberDto();
        memberDto.setMail(member.getMail());
        memberDto.setPw(member.getPw());
        memberDto.setName(member.getName());
        memberDto.setPhone(member.getPhone());
        memberDto.setAddress(member.getAddress());

        return memberDto;
    }

    // 비밀번호 일치 확인
    public boolean isPWEqual() {
        return pw != null && pw.equals(check_pw);
    }

}