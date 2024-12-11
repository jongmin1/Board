package hmgics.board.service;

import hmgics.board.model.Board;
import hmgics.board.mapper.BoardMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public List<Board> findAll() {
        return boardMapper.findAll();
    }

    public List<Board> searchBoards(String keyword) {
        return boardMapper.searchBoards(keyword);
    }

    public Optional<Board> findById(Long id) {
        return Optional.ofNullable(boardMapper.findById(id));
    }

    public void save(Board board) {
        boardMapper.save(board);
    }

    public void update(Board board) {
        boardMapper.update(board);
    }

    public void deleteById(Long id) {
        boardMapper.deleteById(id);
    }
}