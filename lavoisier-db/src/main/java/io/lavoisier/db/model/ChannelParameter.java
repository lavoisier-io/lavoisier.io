package io.lavoisier.db.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * A system wide channel parameter
 */
@Entity
@Table(name = "lav_channel_parameter")
public class ChannelParameter {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "cpr_id")
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "cpr_name", nullable = false, length = 256)
    private String name;

    @Column(name = "cpr_value", nullable = false)
    @Lob
    @Type(type = "org.hibernate.type.StringClobType")
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
