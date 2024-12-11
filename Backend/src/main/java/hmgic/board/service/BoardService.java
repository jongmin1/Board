package hmgic.board.service;

import hmgic.board.entity.Board;
import hmgic.board.repository.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private static final Logger log = LoggerFactory.getLogger(BoardService.class);

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    public Board save(Board board) {
        return boardRepository.save(board);
    }

    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }

    public List<Board> searchBoards(String keyword) {
        log.info("게시글 검색 서비스 - 검색어: {}", keyword);
        return boardRepository.searchBoards(keyword);
    }
}
