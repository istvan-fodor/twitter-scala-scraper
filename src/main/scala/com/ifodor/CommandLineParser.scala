package com.ifodor

import scopt.OptionParser

case class Config(consumerKey: String, consumerSecret: String, accessToken: String, accessTokenSecret: String, file: String, searchTerms: List[String])

class CommandLineParser extends OptionParser[Config]("java -jar twitter-scraper-jar-with-dependencies.jar") {

  val consumerKeySysProp = "TWITTER_CONSUMER_KEY"
  val consumerSecretSysPprop = "TWITTER_CONSUMER_SECRET"
  val accessTokenSysProp = "TWITTER_ACCESS_TOKEN"
  val accessTokenSecretSysProp = "TWITTER_ACCESS_TOKEN_SECRET"

  opt[String]('t', "searchTerms") required () action {
    (arg, c) => c.copy(searchTerms = arg.split(",").toList)
  } text("Search terms/filters. See https://dev.twitter.com/streaming/reference/post/statuses/filter for more")
  opt[String]('f', "file") action {
    (arg, c) => c.copy(file = arg)
  } text("File name for storing tweets, with either relaitve path or absolute. If the file already exists, tweets will be appended at the end")
  opt[String]("consumerKey") required () action {
    (arg, c) =>
      {
        arg match {
          case "system" => c.copy(consumerKey = System.getenv(consumerKeySysProp))
          case _        => c.copy(consumerKey = arg)
        }
      }
  } text (s"Twitter consumer key. If set to system, it reads the system variable $consumerKeySysProp")
  opt[String]("consumerSecret") required () action {
    (arg, c) =>
      {
        arg match {
          case "system" => c.copy(consumerSecret = System.getenv(consumerSecretSysPprop))
          case _        => c.copy(consumerSecret = arg)
        }
      }
  } text (s"Twitter consumer secret. If set to system, it reads the system variable $consumerSecretSysPprop")
  opt[String]("accessToken") required () action {
    (arg, c) =>
      {
        arg match {
          case "system" => c.copy(accessToken = System.getenv(accessTokenSysProp))
          case _        => c.copy(accessToken = arg)
        }
      }
  } text (s"Twitter access token. If set to system, it reads the system variable $accessTokenSysProp")
  opt[String]("accessTokenSecret") required () action {
    (arg, c) =>
      {
        arg match {
          case "system" => c.copy(accessTokenSecret = System.getenv(accessTokenSecretSysProp))
          case _        => c.copy(accessTokenSecret = arg)
        }
      }
  } text (s"Twitter access token secret. If set to system, it reads the system variable $accessTokenSecretSysProp")

}