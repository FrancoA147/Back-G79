package utn.frc.backend.estaciones.repositories;

public interface IdentifierRepository {
    int nextValue(String tableName);
}
