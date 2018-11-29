package ${package}.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ${package}.repository.domain.UserEntity;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends PagingAndSortingRepository<UserEntity, Long> {

    Optional<UserEntity> findByLogin(String login);

    Optional<UserEntity> findByEmail(String email);
}
