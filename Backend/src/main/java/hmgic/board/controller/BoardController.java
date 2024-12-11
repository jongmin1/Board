package hmgic.board.controller;

import hmgic.board.entity.Board;
import hmgic.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/boards")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    private BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public ResponseEntity<List<Board>> getAllBoards(@RequestParam(required = false) String keyword) {
        log.info("게시글 목록 조회 요청 - 검색어: {}", keyword);
        if (keyword != null && !keyword.trim().isEmpty()) {
            return ResponseEntity.ok(boardService.searchBoards(keyword));
        }
        return ResponseEntity.ok(boardService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        log.info("게시글 상세 조회 요청 - id: {}", id);
        Optional<Board> board = boardService.findById(id);
        return board.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
        log.info("게시글 생성 요청");
        return ResponseEntity.ok(boardService.save(board));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody Board updatedBoard) {
        log.info("게시글 수정 요청 - id: {}", id);
        return boardService.findById(id)
                .map(existingBoard -> {
                    existingBoard.setTitle(updatedBoard.getTitle());
                    existingBoard.setContent(updatedBoard.getContent());
                    existingBoard.setAuthor(updatedBoard.getAuthor());
                    return ResponseEntity.ok(boardService.save(existingBoard));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        log.info("게시글 삭제 요청 - id: {}", id);
        if (boardService.findById(id).isPresent()) {
            boardService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
