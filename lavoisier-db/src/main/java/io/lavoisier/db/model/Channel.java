package io.lavoisier.db.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Database representation of a channel
 */
@Entity
@Table(name = "lav_channel")
public class Channel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "cha_id")
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "cha_name", nullable = false, unique = true, length = 256)
    private String name;

    @Column(name = "cha_version", nullable = false, length = 20)
    private String version;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cpr_channel_id", nullable = false)
    private List<ChannelParameter> parameters;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    private List<Trigger> triggers;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    private List<Action> actions;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<ChannelParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<ChannelParameter> parameters) {
        this.parameters = parameters;
    }

    public List<Trigger> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<Trigger> triggers) {
        this.triggers = triggers;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}
