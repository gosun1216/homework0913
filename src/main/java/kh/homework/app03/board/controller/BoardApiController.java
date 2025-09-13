package kh.homework.app03.board.controller;

import kh.homework.app03.board.dto.BoardReqDto;
import kh.homework.app03.board.dto.BoardRespDto;
import kh.homework.app03.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping
    public BoardRespDto insert(@RequestBody BoardReqDto dto) {
        return boardService.insert(dto);
    }

    @GetMapping("{no}")
    public BoardRespDto selectOne(@PathVariable Long no) {
        return boardService.selectOne(no);
    }

    @GetMapping
    public List<BoardRespDto> selectList() {
        return boardService.selectList();
    }

    @PutMapping("{no}")
    public BoardRespDto update(@PathVariable Long no, @RequestBody BoardReqDto dto) {
        return boardService.update(no, dto);
    }

    @DeleteMapping("{no}")
    public void delete(@PathVariable Long no) {
        boardService.delete(no);
    }

    @GetMapping("/search/title")
    public Page<BoardRespDto> searchByTitle(@RequestParam String keyword, Pageable pageable) {
        return boardService.searchByTitle(keyword, pageable);
    }

    @GetMapping("/search/content")
    public Page<BoardRespDto> searchByContent(@RequestParam String keyword, Pageable pageable) {
        return boardService.searchByContent(keyword, pageable);
    }

    @GetMapping("/search/all")
    public Page<BoardRespDto> searchByKeyword(@RequestParam String keyword, Pageable pageable) {
        return boardService.searchByKeyword(keyword, pageable);
    }

    @GetMapping("/paged")
    public Page<BoardRespDto> selectListPaged(Pageable pageable) {
        return boardService.selectListPaged(pageable);
    }
}
