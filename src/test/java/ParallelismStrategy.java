import framework.property.PropertyManager;
import org.junit.platform.engine.ConfigurationParameters;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfiguration;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfigurationStrategy;

public class ParallelismStrategy implements ParallelExecutionConfiguration, ParallelExecutionConfigurationStrategy {

    private final PropertyManager propertyManager = PropertyManager.getInstance();
    private final int poolSize = Integer.parseInt(propertyManager.getProperty("test.poolSize"));
    private final int keepAliveSeconds = Integer.parseInt(propertyManager.getProperty("test.keepAlive"));

    @Override
    public int getParallelism() {
        return poolSize;
    }

    @Override
    public int getMinimumRunnable() {
        return poolSize;
    }

    @Override
    public int getMaxPoolSize() {
        return poolSize;
    }

    @Override
    public int getCorePoolSize() {
        return poolSize;
    }

    @Override
    public int getKeepAliveSeconds() {
        return keepAliveSeconds;
    }

    @Override
    public ParallelExecutionConfiguration createConfiguration(ConfigurationParameters configurationParameters) {
        return this;
    }

}
