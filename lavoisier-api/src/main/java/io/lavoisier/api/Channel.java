package io.lavoisier.api;

import java.io.InputStream;

/**
 * An channel is a collection of Actions and Triggers related to a particular service.
 */
public interface Channel {

    /**
     * @return the inputstream of the xml descriptor of this channel.
     */
    InputStream getDescriptor();

}
