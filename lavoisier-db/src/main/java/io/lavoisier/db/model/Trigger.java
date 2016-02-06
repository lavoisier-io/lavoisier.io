package io.lavoisier.db.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * The database representation of a Trigger
 */
@Entity
@Table(name = "lav_trigger")
public class Trigger {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "tri_id")
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "tri_name", nullable = false, unique = true, length = 256)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tri_channel_id", nullable = false)
    private Channel channel;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
