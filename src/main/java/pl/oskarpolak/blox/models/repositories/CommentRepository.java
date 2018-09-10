package pl.oskarpolak.blox.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.oskarpolak.blox.models.CommentEntity;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Integer> {
    //TEN iNT TO KLUCZ GŁÓWNY Z TABELI

}
