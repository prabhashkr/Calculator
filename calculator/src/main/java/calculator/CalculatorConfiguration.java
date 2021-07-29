package calculator;

import io.confluent.rest.RestConfig;
import org.apache.kafka.common.config.ConfigDef;

public class CalculatorConfiguration extends RestConfig {

    private static final ConfigDef definition;

    static {
        definition=baseConfigDef();

    }

    public CalculatorConfiguration() {
        super(definition);
    }

}
