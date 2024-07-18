package rentaltech.electronics.service;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rentaltech.electronics.dto.QuestionDto;
import rentaltech.electronics.dto.QuestionListDto;
import rentaltech.electronics.entity.Item;
import rentaltech.electronics.entity.Member;
import rentaltech.electronics.entity.Question;
import rentaltech.electronics.repository.ItemRepository;
import rentaltech.electronics.repository.MemberRepository;
import rentaltech.electronics.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public void createQuestion(QuestionDto questionDto, String email) { // 문의 사항 작성

        Optional<Member> optionalMember = memberRepository.findByMail(email);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();

            Item item = itemRepository.findBySerialNum(questionDto.getSerialNum()).orElseThrow(EntityExistsException::new);

            Question question = Question.createQuestion(member, item, questionDto.getTitle(), questionDto.getContent());

            questionRepository.save(question);
        }
    }

    public List<QuestionListDto> questionList(Long serialNum) { // 문의 사항 목록 (사용자)

        List<Question> questions = questionRepository.findByItemSerialNum(serialNum);

        return questions.stream()
                .map(QuestionListDto::questionList)
                .collect(Collectors.toList());
    }

}
