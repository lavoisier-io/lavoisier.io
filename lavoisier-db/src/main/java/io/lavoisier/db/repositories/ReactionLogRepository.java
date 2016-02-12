package io.lavoisier.db.repositories;

import io.lavoisier.db.model.ReactionLog;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReactionLogRepository extends CrudRepository<ReactionLog, UUID> {
}
