package io.lavoisier.db.repositories;

import io.lavoisier.db.model.Channel;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ChannelRepository extends CrudRepository<Channel, UUID> {
}
