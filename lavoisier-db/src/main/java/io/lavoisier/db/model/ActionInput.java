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
