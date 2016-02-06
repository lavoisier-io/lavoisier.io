package io.lavoisier.db.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * A channel activation parameter
 */
@Entity
@Table(name = "lav_channel_activation_parameter")
public class ChannelActivationParameter {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "cap_id")
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "cap_name", nullable = false, length = 256)
    private String name;

    @Column(name = "cap_value", nullable = false, length = 256)
    private String value;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
