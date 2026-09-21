package br.com.lucaspapini.repetilingua.repositories;
import br.com.lucaspapini.repetilingua.entities.dto.TextResumeDataDTO;
import br.com.lucaspapini.repetilingua.entities.dto.TextStatsResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.lucaspapini.repetilingua.entities.Text;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface TextRepository extends JpaRepository<Text, Long> {
    Page<Text> findByUserId(Long id, Pageable pageable);

    List<Text> findByUserId(Long id);

    Integer countTotalByUserId(Long id);

    Integer countTotalTextPartByUserId(Long id);

    @Query(
            """
                SELECT new br.com.lucaspapini.repetilingua.entities.dto.TextResumeDataDTO(
                    t.id,
                    t.title,
                    tp.partNumber,
                    tp.audioPath,
                    t.moment,
                    tpp.day1ReadAndListen,
                    tpp.day1OnlyListen,
                    tpp.day1ReadAndListenFinalCheck,
                    tpp.day2ReadAndListen,
                    tpp.day2OnlyListen,
                    tpp.day2ReadAndListenFinalCheck
                )
                FROM Text t
                INNER JOIN t.textParts tp
                INNER JOIN tp.partProgresses tpp
                WHERE t.user.id = :id
            """
    )
    List<TextResumeDataDTO> findAllTextByUserId(Long id);

    @Query(
            """
                SELECT DISTINCT t
                FROM Text t
                INNER JOIN FETCH t.textParts tp
                WHERE t.user.id = :id
                AND t.id = :textId
            """
    )
    Text findAllTextByUserIdAndByTextId(Long id, Long textId);


    Optional<Text>  findByIdAndUserId(Long textId, Long userId);
}
