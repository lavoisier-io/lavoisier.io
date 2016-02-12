package io.lavoisier.db.repositories;

import io.lavoisier.db.model.ChannelActivation;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ChannelActivationRepository extends CrudRepository<ChannelActivation, UUID> {
}
