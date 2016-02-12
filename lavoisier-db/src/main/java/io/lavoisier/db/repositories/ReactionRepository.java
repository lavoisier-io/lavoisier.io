package io.lavoisier.db.repositories;

import io.lavoisier.db.model.Reaction;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReactionRepository extends CrudRepository<Reaction, UUID> {
}
