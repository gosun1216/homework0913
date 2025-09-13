package kh.homework.app03.board.entity;

import kh.homework.app03.board.dto.BoardReqDto;
import kh.homework.app03.board.dto.BoardRespDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "BOARD")
@Getter
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Column(length = 1, nullable = false)
    private String delYn;

    public BoardEntity() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.delYn = "N";
    }

    public static BoardEntity from(BoardReqDto dto) {
        BoardEntity entity = new BoardEntity();
        entity.title = dto.getTitle();
        entity.content = dto.getContent();
        return entity;
    }

    public static BoardRespDto toDto(BoardEntity entity) {
        BoardRespDto dto = new BoardRespDto();
        dto.setNo(entity.getNo());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setDelYn(entity.getDelYn());
        return dto;
    }

    public void update(BoardReqDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.updatedAt = LocalDateTime.now();
    }

    public void delete() {
        this.delYn = "Y";
        this.updatedAt = LocalDateTime.now();
    }
}
