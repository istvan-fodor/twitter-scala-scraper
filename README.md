Description
-----------

This is a Scala application that you can use to read tweets off of Twitter and store in JSON format. To run, you require Java 8.

Building
--------
### Requirements
* Maven 3
* Java 8

Check out and build: 

    git clone https://github.com/istvan-fodor/twitter-scala-scraper.git
    cd twitter-scala-scraper
    mvn clean install

Usage
-----
After build run:

    cd target
    java -jar twitter-scraper-jar-with-dependencies.jar <arguments>

The program takes multiple arguments:
- `--file <file_name>`: target directory for tweets. If omitted, the tweets are saved to the execution directory
- `--searchTerms <search terms>`: Search terms/filters: generic terms, hashtags, usernames, etc. See https://dev.twitter.com/streaming/overview/request-parameters#track for more
- `--consumerKey <value>`: Twitter consumer key (see https://apps.twitter.com). If omitted, it uses the system variable TWITTER_CONSUMER_KEY.
- `--consumerSecret <value>`: Twitter consumer secret. If omitted, it uses the system variable TWITTER_CONSUMER_SECRET.
- `--accessToken <value>`: Twitter access token. If omitted, it uses the system variable TWITTER_ACCESS_TOKEN.
- `--accessTokenSecret <value>`: Twitter access token secret. If omitted, it uses the system variable TWITTER_ACCESS_TOKEN_SECRET

If you restart the application with the file parameter, it will append tweets to the existing file. Tweets are saved in a JSON format and can be parsed using Twitter4J (http://twitter4j.org/en/index.html) or any other JSON parser.

Example command line:
`java -jar twitter-scraper-jar-with-dependencies.jar --file some_tweets.txt --searchTerms "big data" --consumerKey XYZ --consumerSecret XYZ --accessToken XYZ --accessTokenSecret XYZ`

You can quit the application by pressing `Ctrl-C`

You can also run directly from maven without building the jar with the following command line:

`mvn scala:run@scrape -Dtwitter.file=target/tweets.txt -Dtwitter.searchTerms="big data" -Dtwitter.consumerKey=XYZ -Dtwitter.consumerSecret=XYZ -Dtwitter.accessToken=XYZ -Dtwitter.accessTokenSecret=XYZ`


