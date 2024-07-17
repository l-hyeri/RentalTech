package rentaltech.electronics.service;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rentaltech.electronics.dto.QuestionDto;
import rentaltech.electronics.entity.Item;
import rentaltech.electronics.entity.Member;
import rentaltech.electronics.entity.Question;
import rentaltech.electronics.repository.ItemRepository;
import rentaltech.electronics.repository.MemberRepository;
import rentaltech.electronics.repository.QuestionRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public void createQuestion(QuestionDto questionDto, String email) {

        Optional<Member> optionalMember = memberRepository.findByMail(email);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();

            Item item = itemRepository.findBySerialNum(questionDto.getSerialNum()).orElseThrow(EntityExistsException::new);

            Question question = Question.createQuestion(member, item, questionDto.getTitle(), questionDto.getContent());

            questionRepository.save(question);
        }
    }
}
