package io.lavoisier.db.repositories;

import io.lavoisier.db.model.Action;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ActionRepository extends CrudRepository<Action, UUID> {
}
