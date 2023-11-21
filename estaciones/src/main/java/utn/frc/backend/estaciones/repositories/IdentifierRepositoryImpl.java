package utn.frc.backend.estaciones.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class IdentifierRepositoryImpl implements IdentifierRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public int nextValue(final String tableName){
        Integer res = (Integer)em.createNativeQuery(format("SELECT count(*) FROM %s;", tableName)).getSingleResult();
        return res +1;
    }

}
