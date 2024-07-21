package rentaltech.electronics.dto;

import lombok.Getter;
import lombok.Setter;
import rentaltech.electronics.entity.Question;

@Getter
@Setter
public class QuestionListDto {

    private Long questionId;
    private Long questionItemId;
    private String questionDate;
    private String title;
    private String content;

    public static QuestionListDto questionList(Question question) {
        QuestionListDto dto = new QuestionListDto();
        dto.setQuestionId(question.getId());
        dto.setQuestionItemId(question.getItem().getSerialNum());
        dto.setQuestionDate(question.getQuestionDate());
        dto.setTitle(question.getTitle());
        dto.setContent(question.getContent());

        return dto;
    }
}
