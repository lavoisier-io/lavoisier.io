package io.lavoisier.db.repositories;

import io.lavoisier.db.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}
