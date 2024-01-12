package com.example.sbb.Board;

import com.example.sbb.comment.Comment;
import com.example.sbb.comment.CommentJPARepository;
import com.example.sbb.user.User;
import com.example.sbb.user.UserJPARepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardJPARepository boardJPARepository;
    private final CommentJPARepository commentJPARepository;
    private final UserJPARepository userJPARepository;

    @Transactional
    public List<BoardResponse.BoardListDTO> findAllBoard(int page) {
        // TO-DO : 게시판 목록 조회 Service 로직 생성
        /**
         * 1. 게시판 목록 데이터들을 가져오기
         * 2. 페이징 처리
         * **/
        Pageable pageable = PageRequest.of(page, 10);

        Page<Board> boards = boardJPARepository.findAll(pageable);

        List<BoardResponse.BoardListDTO> boardListDTOList = boards.map(board -> new BoardResponse.BoardListDTO(
                board.getId(),
                board.getTitle(),
                board.getUser().getNickname(),
                board.getCreatedAt()
        )).toList();

        return boardListDTOList;
    }

    @Transactional
    public BoardResponse.BoardDetailDTO findBoardById(int id) {
        // TO-DO : 게시판 상세 조회 Service 로직 생성
        /**
         * 1. 게시판 데이터 가져오기
         * 2. 게시판 데이터와 연관되어 있는 댓글 데이터 가져오기
         */

        Board board = boardJPARepository.findById(id).orElseThrow(RuntimeException::new);

        // board 의 id 를 활용해 댓글 데이터 목록 가져오기
        List<Comment> comments = commentJPARepository.findAllByBoardId(id);

        return new BoardResponse.BoardDetailDTO(board, comments);
    }

    @Transactional
    public int postBoard(BoardRequest.PostDTO postDTO, HttpSession session) {
        // user 인증
        String email = (String)session.getAttribute("USER_EMAIL");

        emailCheck(email);

        // DB 에서 User 정보 가져오기
        User user = userJPARepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        // 게시글 등록하기
        Board board = new Board(user, postDTO.getTitle(), postDTO.getContent());

        boardJPARepository.save(board);

        return board.getId();

    }

    private void emailCheck(String email) {
        if (email == null) {
            // 사용자가 로그인하지 않은 경우 예외 처리
            throw new IllegalStateException("로그인이 필요한 기능입니다.");
        }
    }

    public BoardResponse.BoardListUpdateDTO updateBoard(int id, BoardRequest.PostDTO postDTO, HttpSession session) {
        /**
         * 1. 인증
         * 2. 내가 작성한 게시글이 맞는지 체크
         * 3. 수정
         * 4. 게시글에 해당하는 댓글 조회해서 가져오기
         * **/

        // 세션 인증
        String email = (String)session.getAttribute("USER_EMAIL");

        emailCheck(email);

        // 내가 작성한 게시글이 맞는지 체크
        Board board = boardJPARepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        User user = userJPARepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        if (!board.getUser().equals(user)) {
            throw new IllegalArgumentException("해당 게시글을 수정할 권한이 없습니다.");
        }

        // 수정하기
        board.update(postDTO.getTitle(), postDTO.getContent());

        // 댓글 정보 가져오기
        List<Comment> comments = commentJPARepository.findAllByBoardId(id);

        return new BoardResponse.BoardListUpdateDTO(board, comments);
    }
}
