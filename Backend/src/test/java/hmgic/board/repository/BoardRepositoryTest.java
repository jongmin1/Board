package hmgic.board.repository;

import hmgic.board.entity.Board;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @AfterEach
    void cleanup() {
        boardRepository.deleteAll(); // 테스트 후 데이터 정리
    }

    @Test
    void testSearchBoards() {
        // given
        Board board1 = new Board();
        board1.setTitle("테스트 제목 1");
        board1.setContent("테스트 내용 1");
        board1.setAuthor("작성자 1");
        boardRepository.save(board1);

        Board board2 = new Board();
        board2.setTitle("다른 제목");
        board2.setContent("테스트 검색어 포함");
        board2.setAuthor("작성자 2");
        boardRepository.save(board2);

        Board board3 = new Board();
        board3.setTitle("관련없는 제목");
        board3.setContent("관련없는 내용");
        board3.setAuthor("작성자 3");
        boardRepository.save(board3);

        // when
        List<Board> searchResult1 = boardRepository.searchBoards("테스트");
        List<Board> searchResult2 = boardRepository.searchBoards("관련없는");
        List<Board> searchResult3 = boardRepository.searchBoards("존재하지않는검색어");

        // then
        assertThat(searchResult1).hasSize(2);
        assertThat(searchResult2).hasSize(1);
        assertThat(searchResult3).isEmpty();
    }
}