package com.example.sbb.Board;

import com.example.sbb.config.utils.ApiResponseBuilder;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardRestController {

    private final BoardService boardService;

    @GetMapping("")
    public ResponseEntity<?> getBoard(@RequestParam(value = "page", defaultValue = "0") int page) {
        // TO-DO : 게시판 목록 조회 Service 로직 생성
        List<BoardResponse.BoardListDTO> responseDTO = boardService.findAllBoard(page);

        return ResponseEntity.ok(ApiResponseBuilder.success(responseDTO));
    }

    @PostMapping("")
    public ResponseEntity<?> createBoard(@RequestBody BoardRequest.PostDTO postDTO, HttpSession session) {
        // TO-DO : 게시판 등록 Service 로직 생성
        int boardId = boardService.postBoard(postDTO, session);

        return ResponseEntity.ok(ApiResponseBuilder.success(boardId));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getBoardId(@PathVariable int id) {
        // TO-DO : 게시판 상세 조회 Service 로직 생성
        BoardResponse.BoardDetailDTO responseDTO = boardService.findBoardById(id);

        return ResponseEntity.ok(ApiResponseBuilder.success(responseDTO));
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<?> updateBoard(@PathVariable int id, @RequestBody BoardRequest.PostDTO postDTO, HttpSession session) {
        // TO-DO : 게시판 수정 Service 로직 생성
        BoardResponse.BoardDetailDTO responseDTO = boardService.updateBoard(id, postDTO, session);

        return ResponseEntity.ok(ApiResponseBuilder.success(responseDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable int id, HttpSession session) {
        // TO-DO : 게시판 삭제 Service 로직 생성
        boardService.deleteBoard(id, session);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseBuilder.successWithNoContent());
    }

}
