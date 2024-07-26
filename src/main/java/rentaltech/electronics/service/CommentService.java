package rentaltech.electronics.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rentaltech.electronics.dto.CommentDto;
import rentaltech.electronics.entity.Comment;
import rentaltech.electronics.entity.Member;
import rentaltech.electronics.entity.Question;
import rentaltech.electronics.repository.CommentRepository;
import rentaltech.electronics.repository.MemberRepository;
import rentaltech.electronics.repository.QuestionRepository;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;

    public CommentDto createComment(CommentDto commentDto) {

        Question question = questionRepository.findById(commentDto.getQuestion())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        Member member = memberRepository.findByMail(commentDto.getMember())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Comment comment = Comment.fromDto(commentDto, question, member);
        Comment savedComment = commentRepository.save(comment);

        return CommentDto.fromEntity(savedComment);
    }
}
