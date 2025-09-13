package kh.homework.app03.board.repository;

import kh.homework.app03.board.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Query("SELECT b FROM BoardEntity b WHERE b.delYn = 'N' AND b.title LIKE %:keyword%")
    Page<BoardEntity> searchByTitle(String keyword, Pageable pageable);

    @Query("SELECT b FROM BoardEntity b WHERE b.delYn = 'N' AND b.content LIKE %:keyword%")
    Page<BoardEntity> searchByContent(String keyword, Pageable pageable);

    @Query("SELECT b FROM BoardEntity b WHERE b.delYn = 'N' AND (b.title LIKE %:keyword% OR b.content LIKE %:keyword%)")
    Page<BoardEntity> searchByKeyword(String keyword, Pageable pageable);

    Page<BoardEntity> findByDelYn(String delYn, Pageable pageable);
}
