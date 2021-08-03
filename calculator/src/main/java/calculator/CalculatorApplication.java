package calculator;

import io.confluent.rest.Application;
import org.eclipse.jetty.security.*;
import org.eclipse.jetty.util.security.Constraint;
import org.eclipse.jetty.util.security.Password;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Configurable;
import java.util.HashMap;

public class CalculatorApplication extends Application<CalculatorConfiguration>{

    private static final Logger log= LoggerFactory.getLogger(CalculatorApplication.class);

    public CalculatorApplication(CalculatorConfiguration config) {
        super(config);
    }

    public static void main(String[] args) throws Exception{
        HashMap<String,String> props= new HashMap<>();
        props.put("authentication.method","BASIC");
        CalculatorConfiguration calculatorConfiguration=new CalculatorConfiguration(props);
        CalculatorApplication app =new CalculatorApplication(calculatorConfiguration);
        app.start();
        log.info("Server started, listening on"+app.server.getURI());
        System.out.println("Server started, listening on "+app.server.getURI());
        app.join();
    }

    @Override
    public void setupResources(Configurable<?> configurable, CalculatorConfiguration calculatorConfiguration) {
        final CalculatorResource resource=new CalculatorResource();
        configurable.register(resource);
//        configurable.register(CalculatorExceptionMapper.class);
    }

    @Override
    protected LoginService createLoginService() {
        return newLoginService();
    }

    public static LoginService newLoginService() {
        HashLoginService realm = new HashLoginService();
        realm.setName("default");   // this is the magic realm name to make it effective on everywhere
        UserStore userStore = new UserStore();
        realm.setUserStore(userStore);
        userStore.addUser("ef", new Password("ef"), new String[]{"user"});
        return realm;
    }

    @Override
    protected IdentityService createIdentityService() {
        return null;
    }

    @Override
    protected ConstraintMapping createGlobalAuthConstraint() {
        final Constraint constraint = new Constraint();
        constraint.setAuthenticate(true);
        constraint.setRoles(new String[]{"user"});
        final ConstraintMapping mapping = new ConstraintMapping();
        mapping.setConstraint(constraint);
        mapping.setMethod("*");
        mapping.setPathSpec("/audit");
        return mapping;
    }
}
