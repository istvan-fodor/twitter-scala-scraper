# twitter-scala-scraper

This is a JVM application that you can use to read tweets off of Twitter. To build from source, you need to install Maven and run  
`mvc clean install`. After the build you can execute twitter-scraper-jar-with-dependencies.jar form the target folder: `java -jar twitter-scraper-jar-with-dependencies.jar`

The program takes multiple arguments:
- `—targetDirectory <directory_name>`: target directory for tweets
- `—searchTerms <search terms>`: twitter search terms, hashtags, usernames
- `—consumerKey <value>`: Twitter consumer key (see https://apps.twitter.com). If set to system, it uses the system variable TWITTER_CONSUMER_KEY.
- `—consumerSecret <value>`: Twitter consumer secret. If set to system, it uses the system variable TWITTER_CONSUMER_SECRET.
- `—accessToken <value>`: Twitter access token. If set to system, it uses the system variable TWITTER_ACCESS_TOKEN.
- `—accessTokenSecret <value>`: Twitter access token secret. If set to system, it uses the system variable TWITTER_ACCESS_TOKEN_SECRET

Example command line:
`java -jar twitter-scraper-jar-with-dependencies.jar --targetDirectory . --searchTerms “twitter,twitter api” --consumerKey XYZ --consumerSecret XYZ --accessToken XYZ --accessTokenSecret XYZ`


