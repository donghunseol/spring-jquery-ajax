package shop.mtcoding.blog.board;

import jakarta.persistence.PostUpdate;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardRepository boardRepository;

    @PutMapping("/api/boards/{id}")
    public ApiUtil<?> update(@PathVariable Integer id, @RequestBody BoardRequest.WriteDTO requestDTO, HttpServletResponse response) {
        Board board = boardRepository.selectOne(id);

        if (board == null) {
            response.setStatus(404);
            return new ApiUtil<>(404, "해당 게시글을 찾을 수 없습니다.");
        }
        boardRepository.updateById(requestDTO, id);
        return new ApiUtil<>(requestDTO);
    }

    @PostMapping("/api/boards/{id}")
    public ApiUtil<?> write(@PathVariable Integer id, @RequestBody BoardRequest.WriteDTO requestDTO, HttpServletResponse response) { // JSON으로 받을 수 있는 어노테이션
        Board board = boardRepository.selectOne(id);
        if (board == null) {
            response.setStatus(404);
            return new ApiUtil<>(404, "해당 게시글을 찾을 수 없습니다");
        }
        boardRepository.updateById(requestDTO, id);
        return new ApiUtil<>(null);
    }

    @DeleteMapping("/api/boards/{id}")
    public ApiUtil<?> deleteById(@PathVariable Integer id, HttpServletResponse response) {
        Board board = boardRepository.selectOne(id);
        if (board == null) {
            response.setStatus(404); // 404 를 따로 넣어줘야지 제대로된 값이 들어간다.
            return new ApiUtil<>(404, "해당 게시글을 찾을 수 없습니다");
        }
        boardRepository.deleteById(id);
        return new ApiUtil<>(null);
    }

    @GetMapping("/api/boards") // API라 복수형으로 기입한다.
    public ApiUtil<List<Board>> findAll(HttpServletResponse response) {
        // response.setStatus(401); // 상태 코드 설정하는 코드
        List<Board> boardList = boardRepository.selectAll();
        return new ApiUtil<>(boardList); // MessageConverter (이건 무엇이 될지 모르니깐 추상 클래스이다)
    } // RestController 면서 Object 일 때 MessageConverter가 발동 된다.
}
