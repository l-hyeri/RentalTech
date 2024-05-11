package rentaltech.electronics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 Entity
 */

@Getter
@Setter
@Entity
public class Member{

    @Id
    @Column(name = "member_id")
    private String member_id;   // 아이디

    private String pw;    // 비밀번호

    @Column(name = "member_name")
    private String name;    // 이름

    private String phone;   // 휴대전화

    private String address; // 주소

    private String mail;    // 이메일 주소
}
