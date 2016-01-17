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
        assertThat(testChannel.getName()).isEqualTo("channel.twitter.name");
        assertThat(testChannel.getDescription()).isEqualTo("channel.twitter.description");

        assertThat(testChannel.getGlobalActivationParameters()).isNotNull();
        assertThat(testChannel.getGlobalActivationParameters().size()).isEqualTo(2);
        assertThat(testChannel.getGlobalActivationParameters().get(0)).isNotNull();
        assertThat(testChannel.getGlobalActivationParameters().get(0).getKey()).isEqualTo("channel.twitter.global-activation.app-id");
        assertThat(testChannel.getGlobalActivationParameters().get(0).getType()).isEqualTo(ParameterType.STRING);
        assertThat(testChannel.getGlobalActivationParameters().get(1)).isNotNull();
        assertThat(testChannel.getGlobalActivationParameters().get(1).getKey()).isEqualTo("channel.twitter.global-activation.app-secret");
        assertThat(testChannel.getGlobalActivationParameters().get(1).getType()).isEqualTo(ParameterType.NUMBER);

        assertThat(testChannel.getUserActivationParameters()).isNotNull();
        assertThat(testChannel.getUserActivationParameters().size()).isEqualTo(1);
        assertThat(testChannel.getUserActivationParameters().get(0)).isNotNull();
        assertThat(testChannel.getUserActivationParameters().get(0).getKey()).isEqualTo("channel.twitter.user-activation.token");
        assertThat(testChannel.getUserActivationParameters().get(0).getType()).isEqualTo(ParameterType.DATE);

        assertThat(testChannel.getTriggers()).isNotNull();
        assertThat(testChannel.getTriggers().size()).isEqualTo(2);
        assertThat(testChannel.getTriggers().get(0)).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getName()).isEqualTo("channel.twitter.trigger.mention.name");
        assertThat(testChannel.getTriggers().get(0).getDescription()).isEqualTo("channel.twitter.trigger.mention.description");
        assertThat(testChannel.getTriggers().get(0).getInput()).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getInput()).isEmpty();
        assertThat(testChannel.getTriggers().get(0).getOutput()).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getOutput().size()).isEqualTo(4);
        assertThat(testChannel.getTriggers().get(0).getOutput().get(0)).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getOutput().get(0).getKey()).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getOutput().get(0).getKey()).isEqualTo("channel.twitter.trigger.mention.output.tweet-content");
        assertThat(testChannel.getTriggers().get(0).getOutput().get(1)).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getOutput().get(1).getKey()).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getOutput().get(1).getKey()).isEqualTo("channel.twitter.trigger.mention.output.tweet-author");
        assertThat(testChannel.getTriggers().get(0).getOutput().get(2)).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getOutput().get(2).getKey()).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getOutput().get(2).getKey()).isEqualTo("channel.twitter.trigger.mention.output.tweet-url");
        assertThat(testChannel.getTriggers().get(0).getOutput().get(3)).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getOutput().get(3).getKey()).isNotNull();
        assertThat(testChannel.getTriggers().get(0).getOutput().get(3).getKey()).isEqualTo("channel.twitter.trigger.mention.output.tweet-date");
        assertThat(testChannel.getTriggers().get(1)).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getName()).isEqualTo("channel.twitter.trigger.newtweet.name");
        assertThat(testChannel.getTriggers().get(1).getDescription()).isEqualTo("channel.twitter.trigger.newtweet.description");
        assertThat(testChannel.getTriggers().get(1).getInput()).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getInput().size()).isEqualTo(1);
        assertThat(testChannel.getTriggers().get(1).getInput().get(0)).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getInput().get(0).getKey()).isEqualTo("channel.twitter.trigger.newtweet.input.tweet-author");
        assertThat(testChannel.getTriggers().get(1).getInput().get(0).getType()).isEqualTo(ParameterType.STRING);
        assertThat(testChannel.getTriggers().get(1).getOutput()).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getOutput().size()).isEqualTo(4);
        assertThat(testChannel.getTriggers().get(1).getOutput().get(0)).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getOutput().get(0).getKey()).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getOutput().get(0).getKey()).isEqualTo("channel.twitter.trigger.newtweet.output.tweet-content");
        assertThat(testChannel.getTriggers().get(1).getOutput().get(1)).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getOutput().get(1).getKey()).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getOutput().get(1).getKey()).isEqualTo("channel.twitter.trigger.newtweet.output.tweet-author");
        assertThat(testChannel.getTriggers().get(1).getOutput().get(2)).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getOutput().get(2).getKey()).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getOutput().get(2).getKey()).isEqualTo("channel.twitter.trigger.newtweet.output.tweet-url");
        assertThat(testChannel.getTriggers().get(1).getOutput().get(3)).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getOutput().get(3).getKey()).isNotNull();
        assertThat(testChannel.getTriggers().get(1).getOutput().get(3).getKey()).isEqualTo("channel.twitter.trigger.newtweet.output.tweet-date");

        assertThat(testChannel.getActions()).isNotNull();
        assertThat(testChannel.getActions().size()).isEqualTo(1);
        assertThat(testChannel.getActions().get(0)).isNotNull();
        assertThat(testChannel.getActions().get(0).getName()).isEqualTo("channel.twitter.action.posttweet.name");
        assertThat(testChannel.getActions().get(0).getDescription()).isEqualTo("channel.twitter.action.posttweet.description");
        assertThat(testChannel.getActions().get(0).getInput()).isNotNull();
        assertThat(testChannel.getActions().get(0).getInput().size()).isEqualTo(1);
        assertThat(testChannel.getActions().get(0).getInput().get(0)).isNotNull();
        assertThat(testChannel.getActions().get(0).getInput().get(0).getKey()).isEqualTo("channel.twitter.action.posttweet.input.tweet-content");
        assertThat(testChannel.getActions().get(0).getInput().get(0).getType()).isEqualTo(ParameterType.TEXT);
    }
}
