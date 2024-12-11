package hmgics.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import hmgics.board.model.Board;

@Mapper
public interface BoardMapper {
    List<Board> findAll();

    List<Board> searchBoards(String keyword);

    Board findById(Long id);

    void save(Board board);

    void update(Board board);

    void deleteById(Long id);
}
