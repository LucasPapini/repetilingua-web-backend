package br.com.lucaspapini.repetilingua.repositories;

import br.com.lucaspapini.repetilingua.entities.TextPartProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TextPartProgressRepository   extends JpaRepository<TextPartProgress, Long> {
    TextPartProgress findByIdAndTextPartId(Long id, Long idTextPart);

    List<TextPartProgress> findByUserId(Long id);

    @Query(value = """
            SELECT obj
            FROM TextPartProgress obj
            WHERE obj.user.id = :idUser
            AND obj.textPart.id = :idTextPart
            AND obj.textPart.text.id = :idText
            """)
    Optional<TextPartProgress> findByTextPartIdAndTextIdAndUserId(Long idTextPart, Long idText, Long idUser);

    void deleteAllByTextPartTextIdAndUserId(Long id, Long id1);
}
