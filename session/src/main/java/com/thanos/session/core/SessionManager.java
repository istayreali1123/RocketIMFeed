package tomcat.request.session.redis;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.session.ManagerBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import tomcat.request.session.SerializationUtil;
import tomcat.request.session.SessionContext;
import tomcat.request.session.data.cache.DataCache;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by w00377182 on 2018/6/25.
 */
public class SessionManagerOne extends ManagerBase implements Lifecycle{

    //use interface to implement duotai
    private DataCache dataCache;
    //tomcat serialize instance
    protected SerializationUtil serializer;
    //thread local session context,one thread holds only one session context
    private ThreadLocal<SessionContext> contextThreadLocal = new ThreadLocal<>();
    //session handler valve
    private SessionHandlerValve sessionHandlerValve;
    //session policy set
    private Set<SessionPolicy> sessionPolicy = EnumSet.of(SessionPolicy.DEFAULT);
    //log
    private Log log = LogFactory.getLog(SessionManagerOne.class.getName());

    enum SessionPolicy {
        DEFAULT, SAVE_ON_CHANGE, ALWAYS_SAVE_AFTER_REQUEST;

        static SessionPolicy fromName(String name) {
            for (SessionPolicy policy : SessionPolicy.values()) {
                if (name.equalsIgnoreCase(policy.name())) {
                    return policy;
                }
            }
            //未匹配到policy，直接抛出异常向外层
            throw new IllegalArgumentException("invalid policy name :" + name);
        }

    }

    public boolean getSaveOnChange() {
        return this.sessionPolicy.contains(SessionPolicy.SAVE_ON_CHANGE);
    }

    public boolean getAlwaysSaveAfterRequest() {
        return this.sessionPolicy.contains(SessionPolicy.ALWAYS_SAVE_AFTER_REQUEST);
    }

    //???
    @Override
    public void addLifecycleListener(LifecycleListener var1) {
        super.addLifecycleListener(var1);
    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
       return super.findLifecycleListeners();
    }

    @Override
    public void removeLifecycleListener(LifecycleListener var1) {
        super.removeLifecycleListener(var1);
    }


    @Override
    public void load() throws ClassNotFoundException, IOException {

    }

    @Override
    public void unload() throws IOException {

    }

    @Override
    protected void startInternal() throws LifecycleException {
        super.startInternal();


    }

}
