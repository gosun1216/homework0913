package kh.homework.app03.board.service;

import kh.homework.app03.board.dto.BoardReqDto;
import kh.homework.app03.board.dto.BoardRespDto;
import kh.homework.app03.board.entity.BoardEntity;
import kh.homework.app03.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardRespDto insert(BoardReqDto dto) {
        BoardEntity entity = BoardEntity.from(dto);
        return BoardEntity.toDto(boardRepository.save(entity));
    }

    @Transactional(readOnly = true)
    public BoardRespDto selectOne(Long no) {
        BoardEntity entity = boardRepository.findById(no).orElse(null);
        if (entity == null) {
            throw new RuntimeException("게시글을 찾을 수 없습니다.");
        }
        return BoardEntity.toDto(entity);
    }

    @Transactional(readOnly = true)
    public List<BoardRespDto> selectList() {
        return boardRepository.findByDelYn("N", Pageable.unpaged())
                .stream()
                .map(BoardEntity::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public BoardRespDto update(Long no, BoardReqDto dto) {
        BoardEntity entity = boardRepository.findById(no).orElse(null);
        if (entity == null) {
            throw new RuntimeException("게시글을 찾을 수 없습니다.");
        }
        entity.update(dto);
        return BoardEntity.toDto(boardRepository.save(entity));
    }

    @Transactional
    public void delete(Long no) {
        BoardEntity entity = boardRepository.findById(no).orElse(null);
        if (entity == null) {
            throw new RuntimeException("게시글을 찾을 수 없습니다.");
        }
        entity.delete();
        boardRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Page<BoardRespDto> searchByTitle(String keyword, Pageable pageable) {
        return boardRepository.searchByTitle(keyword, pageable)
                .map(BoardEntity::toDto);
    }

    @Transactional(readOnly = true)
    public Page<BoardRespDto> searchByContent(String keyword, Pageable pageable) {
        return boardRepository.searchByContent(keyword, pageable)
                .map(BoardEntity::toDto);
    }

    @Transactional(readOnly = true)
    public Page<BoardRespDto> searchByKeyword(String keyword, Pageable pageable) {
        return boardRepository.searchByKeyword(keyword, pageable)
                .map(BoardEntity::toDto);
    }

    @Transactional(readOnly = true)
    public Page<BoardRespDto> selectListPaged(Pageable pageable) {
        return boardRepository.findByDelYn("N", pageable)
                .map(BoardEntity::toDto);
    }
}
