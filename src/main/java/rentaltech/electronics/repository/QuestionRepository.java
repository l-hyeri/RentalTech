package rentaltech.electronics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rentaltech.electronics.entity.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
