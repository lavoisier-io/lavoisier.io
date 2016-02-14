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
import java.util.UUID;

/**
 * The database representation of an Action.
 */
@Entity
@Table(name = "lav_action")
public class Action {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "act_id")
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "act_name", nullable = false, unique = true, length = 256)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "act_channel_id", nullable = false)
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
