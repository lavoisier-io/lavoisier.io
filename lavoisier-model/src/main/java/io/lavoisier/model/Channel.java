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
