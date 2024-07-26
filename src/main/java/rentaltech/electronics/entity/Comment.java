package rentaltech.electronics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import rentaltech.electronics.dto.CommentDto;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long id;

    private String comment;

    private String commentDate;  // 댓글 작성 시정

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;  // 댓글이 작성된 질문

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;  // 작성자

    public static Comment fromDto(CommentDto dto, Question question, Member member) {
        Comment comment = new Comment();
        comment.setComment(dto.getComment());
        comment.setCommentDate(String.valueOf(LocalDateTime.parse(dto.getCommentDate())));
        comment.setQuestion(question);
        comment.setMember(member);
        return comment;
    }
}
