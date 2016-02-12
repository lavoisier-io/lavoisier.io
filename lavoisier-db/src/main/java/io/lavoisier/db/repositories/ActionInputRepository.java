package io.lavoisier.db.repositories;

import io.lavoisier.db.model.ActionInput;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ActionInputRepository extends CrudRepository<ActionInput, UUID> {
}
