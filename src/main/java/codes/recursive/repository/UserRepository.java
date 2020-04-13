package codes.recursive.repository;

import codes.recursive.model.User;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

@JdbcRepository(dialect = Dialect.ORACLE)
public interface UserRepository extends CrudRepository<User, UUID> {
    List<User> findByFirstName(String firstName);
}
