package rentaltech.electronics.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rentaltech.electronics.constant.ItemStockStatus;
import rentaltech.electronics.dto.QuestionDto;
import rentaltech.electronics.entity.Item;
import rentaltech.electronics.entity.Member;
import rentaltech.electronics.entity.Question;
import rentaltech.electronics.repository.ItemRepository;
import rentaltech.electronics.repository.MemberRepository;
import rentaltech.electronics.repository.QuestionRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class QuestionServiceTest {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ItemRepository itemRepository;

    public Item saveItem() {
        Item item = new Item();
        item.setSerialNum(1L);
        item.setItem_name("테스트 상품");
        item.setPeriod(100000);
        item.setStockStatus(ItemStockStatus.SELL);
        item.setStock(100);
        item.setDetails("테스트 상품 상세 설명");
        item.setPeriod(1);

        return itemRepository.save(item);
    }

    public Member saveMember() {
        Member member = new Member();
        member.setMail("test@test.com");

        return memberRepository.save(member);
    }

    @Test
    @DisplayName("문의사항 작성 테스트")
    public void question() {
        // Given
        Member member = saveMember();
        Item item = saveItem();

        QuestionDto questionDto = new QuestionDto();
        questionDto.setSerialNum(item.getSerialNum());
        questionDto.setTitle("상품 문의사항 제목");
        questionDto.setContent("상품 문의사항 상세");

        // When
        questionService.createQuestion(questionDto,member.getMail());

        // Then
        List<Question> savedQuestions = questionRepository.findAll();
        assertEquals(1, savedQuestions.size());

        Question savedQuestion = savedQuestions.get(0);
        assertEquals(questionDto.getTitle(), savedQuestion.getTitle());
        assertEquals(questionDto.getContent(), savedQuestion.getContent());
        assertEquals(member.getMail(), savedQuestion.getMember().getMail());
        assertEquals(item.getSerialNum(), savedQuestion.getItem().getSerialNum());
    }
}
