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

package io.lavoisier.osgi;

import io.lavoisier.core.ChannelDescriptorRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collection;

@Component
public class ChannelDescriptorRepositoryImpl implements ChannelDescriptorRepository {

    private ChannelListener listener;

    @Inject
    public ChannelDescriptorRepositoryImpl(ChannelListener listener) {
        this.listener = listener;
    }

    @Override
    public Collection<io.lavoisier.core.channel.xml.Channel> getAllChannels() {
        return listener.getAllChannels();
    }

    @Override
    public io.lavoisier.core.channel.xml.Channel getChannel(String channelId) {
        return listener.getChannel(channelId);
    }

}
