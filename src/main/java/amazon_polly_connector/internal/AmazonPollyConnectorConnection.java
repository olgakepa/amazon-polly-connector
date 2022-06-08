package amazon_polly_connector.internal;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.polly.PollyClient;
import software.amazon.awssdk.services.polly.model.DescribeVoicesRequest;
import software.amazon.awssdk.services.polly.model.DescribeVoicesResponse;
import software.amazon.awssdk.services.polly.model.Engine;
import software.amazon.awssdk.services.polly.model.Voice;

/**
 * This class represents an extension connection just as example (there is no real connection with anything here c:).
 */
public final class AmazonPollyConnectorConnection {

	private Voice voice;
	private PollyClient polly;

  public AmazonPollyConnectorConnection(String accessKey,String accessSecret, String awsRegion, String voiceName, String engine) {
	  
	  AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(accessKey, accessSecret);
		StaticCredentialsProvider staticCredentialsProvider = StaticCredentialsProvider.create(awsBasicCredentials);
		
		Region region = Region.regions().stream().filter(e -> e.id().equalsIgnoreCase(awsRegion)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
		
		polly = PollyClient.builder().credentialsProvider(staticCredentialsProvider).region(region).build();
		
		DescribeVoicesRequest describeVoiceRequest = DescribeVoicesRequest.builder()
				.engine(Engine.fromValue(engine.toLowerCase())).build();

		
		DescribeVoicesResponse describeVoicesResult = polly.describeVoices(describeVoiceRequest);
		
		voice = describeVoicesResult.voices().stream().filter(e -> e.name().equalsIgnoreCase(voiceName)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	  
  }

  public PollyClient getPollyClient() {
	  return polly;
  }
  
  public Voice getVoice() {
	  return voice;
  }

  public void invalidate() {
    // do something to invalidate this connection!
  }
}
