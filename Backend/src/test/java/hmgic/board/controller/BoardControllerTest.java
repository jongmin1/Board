package hmgic.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hmgic.board.entity.Board;
import hmgic.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateBoard() throws Exception {
        // 테스트 데이터
        Board board = new Board();
        board.setTitle("Test Title");
        board.setContent("Test Content");
        board.setAuthor("Test Author");

        // Mock Service 동작 정의
        when(boardService.save(any(Board.class))).thenReturn(board);

        // JSON 변환
        String boardJson = objectMapper.writeValueAsString(board);

        // POST 요청 테스트
        mockMvc.perform(post("/api/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(boardJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
