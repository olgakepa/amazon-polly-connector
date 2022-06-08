package amazon_polly_connector.internal;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.io.InputStream;

import org.mule.runtime.extension.api.annotation.param.MediaType;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.polly.model.OutputFormat;
import software.amazon.awssdk.services.polly.model.SynthesizeSpeechRequest;
import software.amazon.awssdk.services.polly.model.SynthesizeSpeechResponse;

import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;


/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class AmazonPollyConnectorOperations {

  /**
   * Example of an operation that uses the configuration and a connection instance to perform some action.
   */
  @MediaType(value = ANY, strict = false)
  public InputStream textToSpeech(@Connection AmazonPollyConnectorConnection connection, String text){
  
	  SynthesizeSpeechRequest synthReq = SynthesizeSpeechRequest.builder()
			  .text(text)
			  .voiceId(connection.getVoice().id())
			  .outputFormat(OutputFormat.MP3)
			  .build();
	  ResponseInputStream<SynthesizeSpeechResponse> synthRes = connection
			  .getPollyClient()
			  .synthesizeSpeech(synthReq);
	  
	  return synthRes;
  
  }
  
}
