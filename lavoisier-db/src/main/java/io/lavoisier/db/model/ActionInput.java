package io.lavoisier.db.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * Input parameter of an action.
 */
@Entity
@Table(name = "lav_action_input")
public class ActionInput {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ain_id")
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "ain_name", nullable = false, length = 256)
    private String name;

    @Column(name = "ain_value", nullable = false)
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
