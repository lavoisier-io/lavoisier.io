package io.lavoisier.db.repositories;

import io.lavoisier.db.model.ChannelActivationParameter;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ChannelActivationParameterRepository extends CrudRepository<ChannelActivationParameter, UUID> {
}
