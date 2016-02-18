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

package io.lavoisier.core.channel.xml;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(BlockJUnit4ClassRunner.class)
public class JaxbUnmarshallingTest {

    @Test
    public void testUnmarshalling() throws Exception {
        JAXBContext context = JAXBContext.newInstance(Channel.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Channel testChannel = (Channel) unmarshaller.unmarshal(getClass().getClassLoader().getResource("exemple-channel.xml"));

        assertThat(testChannel).isNotNull();
        assertThat(testChannel.getId()).isEqualTo("io.lavoisier.channel.twitter");
        assertThat(testChannel.getName()).isEqualTo("Twitter");
        assertThat(testChannel.getDescription()).isEqualTo("Connect to the famous Twitter social network!");

        assertThat(testChannel.getGlobalActivationParameters()).isNotNull();
        assertThat(testChannel.getGlobalActivationParameters().size()).isEqualTo(2);
        assertThat(testChannel.getGlobalActivationParameters().get(0)).isNotNull();
        assertThat(testChannel.getGlobalActivationParameters().get(0).getKey()).isEqualTo("appId");
        assertThat(testChannel.getGlobalActivationParameters().get(0).getType()).isEqualTo(ParameterType.STRING);
        assertThat(testChannel.getGlobalActivationParameters().get(1)).isNotNull();
        assertThat(testChannel.getGlobalActivationParameters().get(1).getKey()).isEqualTo("appSecret");
        assertThat(testChannel.getGlobalActivationParameters().get(1).getType()).isEqualTo(ParameterType.NUMBER);

        assertThat(testChannel.getUserActivationParameters()).isNotNull();
        assertThat(testChannel.getUserActivationParameters().size()).isEqualTo(1);
        assertThat(testChannel.getUserActivationParameters().get(0)).isNotNull();
        assertThat(testChannel.getUserActivationParameters().get(0).getKey()).isEqualTo("userToken");
        assertThat(testChannel.getUserActivationParameters().get(0).getType()).isEqualTo(ParameterType.STRING);

        assertThat(testChannel.getTriggers()).isNotNull();
        assertThat(testChannel.getTriggers().size()).isEqualTo(2);
        assertThat(testChannel.getTriggers().get(0)).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getId()).isEqualTo("mention");
        assertThat(testChannel.getTriggers().get(0).getName()).isEqualTo("Mention");
        assertThat(testChannel.getTriggers().get(0).getDescription()).isEqualTo("Someone mentions you");
        assertThat(testChannel.getTriggers().get(0).getInput()).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getInput()).isEmpty();
        assertThat(testChannel.getTriggers().get(0).getOutput()).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getOutput().size()).isEqualTo(4);
        assertThat(testChannel.getTriggers().get(0).getOutput().get(0)).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getOutput().get(0).getKey()).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getOutput().get(0).getKey()).isEqualTo("tweetContent");
        assertThat(testChannel.getTriggers().get(0).getOutput().get(1)).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getOutput().get(1).getKey()).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getOutput().get(1).getKey()).isEqualTo("tweetAuthor");
        assertThat(testChannel.getTriggers().get(0).getOutput().get(2)).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getOutput().get(2).getKey()).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getOutput().get(2).getKey()).isEqualTo("tweetUrl");
        assertThat(testChannel.getTriggers().get(0).getOutput().get(3)).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getOutput().get(3).getKey()).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getOutput().get(3).getKey()).isEqualTo("tweetDate");
        assertThat(testChannel.getTriggers().get(1)).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getId()).isEqualTo("newtweetfromuser");
        assertThat(testChannel.getTriggers().get(1).getName()).isEqualTo("New tweet from user");
        assertThat(testChannel.getTriggers().get(1).getDescription()).isEqualTo("A specific user post a new tweet");
        assertThat(testChannel.getTriggers().get(1).getInput()).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getInput().size()).isEqualTo(1);
        assertThat(testChannel.getTriggers().get(1).getInput().get(0)).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getInput().get(0).getKey()).isEqualTo("username");
        assertThat(testChannel.getTriggers().get(1).getInput().get(0).getType()).isEqualTo(ParameterType.STRING);
        assertThat(testChannel.getTriggers().get(1).getOutput()).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getOutput().size()).isEqualTo(4);
        assertThat(testChannel.getTriggers().get(1).getOutput().get(0)).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getOutput().get(0).getKey()).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getOutput().get(0).getKey()).isEqualTo("tweetContent");
        assertThat(testChannel.getTriggers().get(1).getOutput().get(1)).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getOutput().get(1).getKey()).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getOutput().get(1).getKey()).isEqualTo("tweetAuthor");
        assertThat(testChannel.getTriggers().get(1).getOutput().get(2)).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getOutput().get(2).getKey()).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getOutput().get(2).getKey()).isEqualTo("tweetUrl");
        assertThat(testChannel.getTriggers().get(1).getOutput().get(3)).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getOutput().get(3).getKey()).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getOutput().get(3).getKey()).isEqualTo("tweetDate");

        assertThat(testChannel.getActions()).isNotNull();
        assertThat(testChannel.getActions().size()).isEqualTo(1);
        assertThat(testChannel.getActions().get(0)).isNotNull();
        assertThat(testChannel.getActions().get(0).getId()).isEqualTo("posttweet");
        assertThat(testChannel.getActions().get(0).getName()).isEqualTo("Tweet");
        assertThat(testChannel.getActions().get(0).getDescription()).isEqualTo("Post a tweet");
        assertThat(testChannel.getActions().get(0).getInput()).isNotNull();
        assertThat(testChannel.getActions().get(0).getInput().size()).isEqualTo(1);
        assertThat(testChannel.getActions().get(0).getInput().get(0)).isNotNull();
        assertThat(testChannel.getActions().get(0).getInput().get(0).getKey()).isEqualTo("tweetContent");
        assertThat(testChannel.getActions().get(0).getInput().get(0).getType()).isEqualTo(ParameterType.TEXT);
    }
}
