package hmgic.board.service;

import hmgic.board.entity.Board;
import hmgic.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Optional<Board> findById(Long id){
        return boardRepository.findById(id);
    }

    public Board save(Board board){
        return boardRepository.save(board);
    }

    public void deleteById(Long id){
        boardRepository.deleteById(id);
    }
}
