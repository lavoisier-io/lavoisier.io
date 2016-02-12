package io.lavoisier.db.repositories;

import io.lavoisier.db.model.ChannelParameter;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ChannelParameterRepository extends CrudRepository<ChannelParameter, UUID> {
}
