package rentaltech.electronics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id")
    private Long id;    // 문의사항 작성글 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serialNum")
    private Item item;

    private String title;

    private String content;

    private String questionDate; // 글 작성 날짜

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Comment> commentList;  // 댓글 목록

    public static Question createQuestion(Member member, Item item, String title, String content) {
        Question question = new Question();
        question.setMember(member);
        question.setItem(item);
        question.setTitle(title);
        question.setContent(content);
        question.setQuestionDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        return question;
    }

}
