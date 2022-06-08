To use it your project, please install it locally using:

 ```mvn clean install -DskipTests```

and add this snippet to the pom file of your project:

	<dependency>
			<groupId>uk.co.acme</groupId>
			<artifactId>amazon-polly-connector</artifactId>
			<version>1.0.0-SNAPSHOT</version>
			<classifier>mule-plugin</classifier>
	</dependency>
