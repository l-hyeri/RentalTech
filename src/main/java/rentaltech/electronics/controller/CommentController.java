package rentaltech.electronics.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rentaltech.electronics.dto.CommentDto;
import rentaltech.electronics.service.CommentService;

@Controller
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments/write")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {

        CommentDto createdComment = commentService.createComment(commentDto);
        return ResponseEntity.ok(createdComment);
    }
}
