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

package io.lavoisier.db.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * A channel activation by a specific user with his own parameters
 */
@Entity
@Table(name = "lav_channel_activation")
public class ChannelActivation {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "cac_id")
    @Type(type = "pg-uuid")
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cac_channel_id", nullable = false)
    private Channel channel;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cap_channel_activation_id", nullable = false)
    private List<ChannelActivationParameter> parameters;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cac_user_id", nullable = false)
    private User user;

    public UUID getId() {
        return id;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public List<ChannelActivationParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<ChannelActivationParameter> parameters) {
        this.parameters = parameters;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
