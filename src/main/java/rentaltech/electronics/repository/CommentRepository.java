package rentaltech.electronics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rentaltech.electronics.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
