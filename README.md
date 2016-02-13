# twitter-scala-scraper

This is a Scala application that you can use to read tweets off of Twitter. To build from source, you need Java 8 and Maven 3. In the cloned directory build with  `mvc clean install`. After the build finished you can execute twitter-scraper-jar-with-dependencies.jar form the target folder: 
`cd target
java -jar twitter-scraper-jar-with-dependencies.jar`

The program takes multiple arguments:
- `—targetDirectory <directory_name>`: target directory for tweets
- `—searchTerms <search terms>`: twitter search terms, hashtags, usernames
- `—consumerKey <value>`: Twitter consumer key (see https://apps.twitter.com). If set to system, it uses the system variable TWITTER_CONSUMER_KEY.
- `—consumerSecret <value>`: Twitter consumer secret. If set to system, it uses the system variable TWITTER_CONSUMER_SECRET.
- `—accessToken <value>`: Twitter access token. If set to system, it uses the system variable TWITTER_ACCESS_TOKEN.
- `—accessTokenSecret <value>`: Twitter access token secret. If set to system, it uses the system variable TWITTER_ACCESS_TOKEN_SECRET

Example command line:
`java -jar twitter-scraper-jar-with-dependencies.jar --targetDirectory . --searchTerms “twitter,twitter api” --consumerKey XYZ --consumerSecret XYZ --accessToken XYZ --accessTokenSecret XYZ`


