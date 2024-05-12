package rentaltech.electronics.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class joinFormDto {


    @NotNull(message = "아이디 입력은 필수입니다.")
    private String member_id;

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

    private String mail;

    // 비밀번호 일치 확인
    public boolean isPWEqual() {
        return pw != null && pw.equals(check_pw);
    }

}