package io.lavoisier.db.repositories;

import io.lavoisier.db.model.Trigger;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TriggerRepository extends CrudRepository<Trigger, UUID> {
}
