package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardRepository boardRepository;

    @GetMapping("/api/boards") // API라 복수형으로 기입한다.
    public ApiUtil<List<Board>> findAll(HttpServletResponse response){
        // response.setStatus(401); // 상태 코드 설정하는 코드
        List<Board> boardList = boardRepository.selectAll();
        return new ApiUtil<>(boardList); // MessageConverter (이건 무엇이 될지 모르니깐 추상 클래스이다)
    } // RestController 면서 Object 일 때 MessageConverter가 발동 된다.
}
