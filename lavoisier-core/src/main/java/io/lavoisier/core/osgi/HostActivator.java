package io.lavoisier.core.osgi;

import io.lavoisier.core.osgi.utils.CommonsLoggingAdaptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.*;
import org.osgi.service.log.LogReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * The HostActivator registers various Services for availability in the osgi container.
 */
@Component
public class HostActivator implements BundleActivator, ServiceListener {

    private static final Log log = LogFactory.getLog(HostActivator.class);

    @Autowired
    private ApplicationContext applicationContext;

    private CommonsLoggingAdaptor adaptor = new CommonsLoggingAdaptor();

    private LinkedList<LogReaderService> readers = new LinkedList<LogReaderService>();

    private BundleContext context;

    @Override
    public void start(BundleContext context) throws Exception {
        this.context = context;

        // Register this class as a listener to updates of the service list
        String filter = "(objectclass=" + LogReaderService.class.getName() + ")";
        try {
            context.addServiceListener(this, filter);
        } catch (InvalidSyntaxException e) {
            assert false : "addServiceListener failed: " + e.getMessage();
        }

        // Register the CommonsLoggingAdaptor to all the LogReaderService objects available
        // on the server.
        ServiceReference[] refs = context.getServiceReferences(LogReaderService.class.getName(), null);
        if (refs != null) {
            for (ServiceReference ref : refs) {
                LogReaderService service = (LogReaderService) context.getService(ref);
                if (service != null) {
                    readers.add(service);
                    service.addLogListener(adaptor);
                }
            }
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        for (Iterator<LogReaderService> i = readers.iterator(); i.hasNext(); ) {
            LogReaderService lrs = i.next();
            lrs.removeLogListener(adaptor);
            i.remove();
        }
        SpringApplication.exit(applicationContext, (() -> 0));
    }

    //  We use a ServiceListener to dynamically keep track of all the LogReaderService service being
    //  registered or unregistered
    @Override
    public void serviceChanged(ServiceEvent event) {
        LogReaderService lrs = (LogReaderService) context.getService(event.getServiceReference());
        if (lrs != null) {
            if (event.getType() == ServiceEvent.REGISTERED) {
                readers.add(lrs);
                lrs.addLogListener(adaptor);
            } else if (event.getType() == ServiceEvent.UNREGISTERING) {
                lrs.removeLogListener(adaptor);
                readers.remove(lrs);
            }
        }
    }

    public Bundle[] getBundles() {
        Bundle[] bundles = null;
        if (context != null) {
            bundles = context.getBundles();
        }
        return bundles;
    }
}