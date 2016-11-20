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

package io.lavoisier.model.channel;

import io.lavoisier.model.Action;
import io.lavoisier.model.spark.Spark;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lav_channel")
@Data
public class Channel {

    @Id
    @Column(name = "id", nullable = false, unique = true, length = 64)
    private String id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "description", nullable = false, length = 256)
    private String description;

    @Column(name = "version", nullable = false, length = 20)
    private String version;

    @OneToMany
    @JoinColumn(name = "channel_id")
    private List<ChannelParameter> parameters;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    private List<Spark> sparks;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    private List<Action> actions;
}
