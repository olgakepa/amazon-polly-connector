package amazon_polly_connector.internal;

import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.connection.PoolingConnectionProvider;
import org.mule.runtime.api.connection.ConnectionProvider;
import org.mule.runtime.api.connection.CachedConnectionProvider;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Password;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class (as it's name implies) provides connection instances and the funcionality to disconnect and validate those
 * connections.
 * <p>
 * All connection related parameters (values required in order to create a connection) must be
 * declared in the connection providers.
 * <p>
 * This particular example is a {@link PoolingConnectionProvider} which declares that connections resolved by this provider
 * will be pooled and reused. There are other implementations like {@link CachedConnectionProvider} which lazily creates and
 * caches connections or simply {@link ConnectionProvider} if you want a new connection each time something requires one.
 */
public class AmazonPollyConnectorConnectionProvider implements PoolingConnectionProvider<AmazonPollyConnectorConnection> {

	@Parameter
	  @DisplayName("AWS Key")
	  @Password
	  private String accessKey;
	  
	  @Parameter
	  @DisplayName("AWS Secret Key")
	  @Password
	  private String accessSecret;
	 
	  @Parameter
	  @DisplayName("AWS Region")
	  @Optional(defaultValue = "us-east-1") 
	  private String awsRegion;
	  
	  
	  @Parameter
	  @DisplayName("Voice")
	  @Optional(defaultValue= "aditi")
	  private String voiceName;
	  
	  @Parameter
	  @DisplayName("Engine")
	  @Optional (defaultValue = "standard")
	  private String engine;

  @Override
  public AmazonPollyConnectorConnection connect() throws ConnectionException {
    return new AmazonPollyConnectorConnection(accessKey, accessSecret, awsRegion, voiceName, engine);
  }

  @Override
  public void disconnect(AmazonPollyConnectorConnection connection) {
 
      connection.invalidate();
    
   
  }

  @Override
  public ConnectionValidationResult validate(AmazonPollyConnectorConnection connection) {
    return ConnectionValidationResult.success();
  }
}
