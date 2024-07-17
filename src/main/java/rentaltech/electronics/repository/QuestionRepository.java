package rentaltech.electronics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rentaltech.electronics.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
