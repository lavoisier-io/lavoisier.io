/*
 * Copyright (C) 2016 Lavoisier.io
 *
 * This file is part of the Lavoisier.io project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.lavoisier.model;

import io.lavoisier.model.spark.Spark;
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
    private Spark spark;

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

    public Spark getSpark() {
        return spark;
    }

    public void setSpark(Spark spark) {
        this.spark = spark;
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
