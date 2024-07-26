package rentaltech.electronics.dto;

import lombok.Getter;
import lombok.Setter;
import rentaltech.electronics.entity.Comment;

@Getter
@Setter
public class CommentDto {

    private Long id;
    private String comment;
    private String commentDate;
    private String member;
    private Long question;

    public static CommentDto fromEntity(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setComment(comment.getComment());
        dto.setCommentDate(comment.getCommentDate().toString());
        dto.setMember(comment.getMember().getMail());
        dto.setQuestion(comment.getQuestion().getId());

        return dto;
    }
}
