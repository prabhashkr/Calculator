package calculator;

import io.confluent.rest.RestConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

public class CalculatorConfiguration extends RestConfig {

    private static final ConfigDef definition;

    static {
        definition=baseConfigDef();
    }

    public CalculatorConfiguration(Map<?, ?> props) {
        super(definition, props);
    }

    public CalculatorConfiguration() {
        super(definition);
    }

}
