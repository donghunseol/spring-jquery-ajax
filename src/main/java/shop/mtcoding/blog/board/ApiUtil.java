package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiUtil<T> {
    private Integer status; // 200, 400, 404, 405
    private String msg; // 성공, 실패 시 -> 정확한 메시지
    private T body; // 어떤 값이 올지 알 수 없으니 제네릭 사용 new 할때 타입을 아니깐 제네릭 사용! (오브젝트 x)\

    public ApiUtil(T body){
        this.status = 200;
        this.msg = "성공";
        this.body = body;
    }

    public ApiUtil(Integer status, String msg){
        this.status = status;
        this.msg = msg;
        this.body = null;
    }
}
