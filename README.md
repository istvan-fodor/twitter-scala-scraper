# twitter-scala-scraper

This is a Scala application that you can use to read tweets off of Twitter. To build from source, you need Java 8 and Maven 3. In the cloned directory build with  `mvn clean install`. After the build finished you can execute twitter-scraper-jar-with-dependencies.jar form the target folder on the command line: 

`cd target`

`java -jar twitter-scraper-jar-with-dependencies.jar`

The program takes multiple arguments:
- `--file <file_name>`: target directory for tweets. If omitted, the tweets are saved to the execution directory
- `--searchTerms <search terms>`: Search terms/filters: generic terms, hashtags, usernames, etc. See https://dev.twitter.com/streaming/overview/request-parameters#track for more
- `--consumerKey <value>`: Twitter consumer key (see https://apps.twitter.com). If set to system, it uses the system variable TWITTER_CONSUMER_KEY.
- `--consumerSecret <value>`: Twitter consumer secret. If set to system, it uses the system variable TWITTER_CONSUMER_SECRET.
- `--accessToken <value>`: Twitter access token. If set to system, it uses the system variable TWITTER_ACCESS_TOKEN.
- `--accessTokenSecret <value>`: Twitter access token secret. If set to system, it uses the system variable TWITTER_ACCESS_TOKEN_SECRET

Tweets are saved to a file whose name is derived from the search terms. If you restart the application with the same keywords, it will 

Example command line:
`java -jar twitter-scraper-jar-with-dependencies.jar --file some_tweets.txt --searchTerms "big data" --consumerKey XYZ --consumerSecret XYZ --accessToken XYZ --accessTokenSecret XYZ`

You can quit the application by pressing `Ctrl-C`

You can also run directly from maven without building the jar with the following command line:

`mvn scala:run@scrape -Dtwitter.file=target/tweets.txt -Dtwitter.searchTerms="big data" -Dtwitter.consumerKey=XYZ -Dtwitter.consumerSecret=XYZ -Dtwitter.accessToken=XYZ -Dtwitter.accessTokenSecret=XYZ`


