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

package io.lavoisier.model.reaction;

import io.lavoisier.model.account.User;
import io.lavoisier.model.action.Action;
import io.lavoisier.model.condition.Condition;
import io.lavoisier.model.fuel.Fuel;
import io.lavoisier.model.spark.Spark;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "lav_reaction")
@Data
public class Reaction {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id")
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumns({
        @JoinColumn(name = "spark_channel_id", referencedColumnName = "channel_id"),
        @JoinColumn(name = "spark_id", referencedColumnName = "spark_id")
    })
    private Spark spark;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "condition_channel_id", referencedColumnName = "channel_id"),
        @JoinColumn(name = "condition_id", referencedColumnName = "condition_id")
    })
    private Condition condition;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "fuel_channel_id", referencedColumnName = "channel_id"),
        @JoinColumn(name = "fuel_id", referencedColumnName = "fuel_id")
    })
    private Fuel fuel;

    @ManyToOne(optional = false)
    @JoinColumns({
        @JoinColumn(name = "action_channel_id", referencedColumnName = "channel_id"),
        @JoinColumn(name = "action_id", referencedColumnName = "action_id")
    })
    private Action action;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "reaction_id", nullable = false)
    @OrderBy("executionDate DESC")
    private List<ReactionLog> logs;
}
