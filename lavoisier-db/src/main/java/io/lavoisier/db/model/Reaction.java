package io.lavoisier.db.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * A reaction
 */
@Entity
@Table(name = "lav_reaction")
public class Reaction {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "rea_id")
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "rea_enabled")
    private boolean enabled;

    @Column(name = "rea_name", length = 150)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rea_user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rea_trigger_id", nullable = false)
    private Trigger trigger;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rea_action_id", nullable = false)
    private Action action;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ain_reaction_id", nullable = false)
    private List<ActionInput> actionInputs;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "log_reaction_id", nullable = false)
    @OrderBy("executionDate DESC")
    private List<ReactionLog> logs;

    public UUID getId() {
        return id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<ActionInput> getActionInputs() {
        return actionInputs;
    }

    public void setActionInputs(List<ActionInput> actionInputs) {
        this.actionInputs = actionInputs;
    }

    public List<ReactionLog> getLogs() {
        return logs;
    }

    public void setLogs(List<ReactionLog> logs) {
        this.logs = logs;
    }
}
