package calculator;

import io.confluent.rest.Application;

import javax.ws.rs.core.Configurable;

public class CalculatorApplication extends Application<CalculatorConfiguration>{

    public CalculatorApplication(CalculatorConfiguration config) {
        super(config);
    }

    public static void main(String[] args) throws Exception{
        CalculatorConfiguration calculatorConfiguration=new CalculatorConfiguration();
        CalculatorApplication app =new CalculatorApplication(calculatorConfiguration);
        app.start();
    }

    @Override
    public void setupResources(Configurable<?> configurable, CalculatorConfiguration calculatorConfiguration) {
        final CalculatorResource resource=new CalculatorResource();
        configurable.register(resource);
    }

}
