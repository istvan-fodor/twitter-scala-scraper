package com.ifodor.twitterscraper

import scopt.OptionParser

case class Config(consumerKey: String, consumerSecret: String, accessToken: String, accessTokenSecret: String, file: String, searchTerms: List[String])

object CommandLineParser {
  val consumerKeySysProp = "TWITTER_CONSUMER_KEY"
  val consumerSecretSysPprop = "TWITTER_CONSUMER_SECRET"
  val accessTokenSysProp = "TWITTER_ACCESS_TOKEN"
  val accessTokenSecretSysProp = "TWITTER_ACCESS_TOKEN_SECRET"
}

class CommandLineParser extends OptionParser[Config]("java -jar twitter-scraper-jar-with-dependencies.jar") {
  opt[String]('t', "searchTerms") required () action {
    (arg, c) => c.copy(searchTerms = arg.split(",").toList)
  } text ("Search terms/filters. See https://dev.twitter.com/streaming/reference/post/statuses/filter for more")
  opt[String]('f', "file") action {
    (arg, c) => c.copy(file = arg)
  } text ("File name for storing tweets, with either relaitve path or absolute. If the file already exists, tweets will be appended at the end")
  opt[String]("consumerKey") action {
    (arg, c) => c.copy(consumerKey = arg)
  } text (s"Twitter consumer key. If omitted, it reads the system variable $CommandLineParser.consumerKeySysProp")
  opt[String]("consumerSecret") action {
    (arg, c) => c.copy(consumerSecret = arg)
  } text (s"Twitter consumer secret. If omitted, it reads the system variable $CommandLineParser.consumerSecretSysPprop")
  opt[String]("accessToken") action {
    (arg, c) => c.copy(accessToken = arg)
  } text (s"Twitter access token. If omitted, it reads the system variable $CommandLineParser.accessTokenSysProp")
  opt[String]("accessTokenSecret") action {
    (arg, c) => c.copy(accessTokenSecret = arg)
  } text (s"Twitter access token secret. If omitted, it reads the system variable $CommandLineParser.accessTokenSecretSysProp")

}