package br.com.lucaspapini.repetilingua.repositories;

import br.com.lucaspapini.repetilingua.entities.TextPart;
import br.com.lucaspapini.repetilingua.entities.dto.TextPartStatsDataDTO;
import br.com.lucaspapini.repetilingua.entities.dto.TextPartStatsResponseDTO;
import br.com.lucaspapini.repetilingua.entities.dto.TextPartStudyResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextPartRepository extends JpaRepository<TextPart, Long> {
    boolean existsByPartNumberAndTextId(Integer pathNumber, Long textId);

    @Query(
            """
                SELECT new br.com.lucaspapini.repetilingua.entities.dto.TextPartStatsDataDTO(
                  tp.id,
                  tp.partNumber,
                  tp.content,
                  tp.audioPath,
                  tpp.day1ReadAndListen,
                  tpp.day1OnlyListen,
                  tpp.day1ReadAndListenFinalCheck,
                  tpp.day2ReadAndListen,
                  tpp.day2OnlyListen,
                  tpp.day2ReadAndListenFinalCheck,
                  tpp.complete
                )
                    FROM TextPart tp
                    INNER JOIN tp.text t
                    INNER JOIN tp.partProgresses tpp
                    WHERE t.user.id = :userId
                    AND t.id = :id
            """
    )
    List<TextPartStatsDataDTO> findAllTextPartByUserAndTextId(Long userId, Long id);

    @Query(
            """
                SELECT new br.com.lucaspapini.repetilingua.entities.dto.TextPartStudyResponseDTO(
                  t.title,
                  tp.id,
                  tp.partNumber,
                  tp.content,
                  tp.audioPath,
                  tpp.day1ReadAndListen,
                  tpp.day1OnlyListen,
                  tpp.day1ReadAndListenFinalCheck,
                  tpp.day2ReadAndListen,
                  tpp.day2OnlyListen,
                  tpp.day2ReadAndListenFinalCheck
                )
                 FROM TextPart tp
                 INNER JOIN tp.text t
                 INNER JOIN tp.partProgresses tpp
                 WHERE t.user.id = :userId
                 AND t.id = :textId
                 AND tp.partNumber = :partNumber
            """
    )
    TextPartStudyResponseDTO findTextPartStudy(Long userId, Long textId, Long partNumber);

    void deleteAllByTextId(Long id);
}
