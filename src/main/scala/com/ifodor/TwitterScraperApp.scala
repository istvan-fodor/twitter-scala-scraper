package com.ifodor

import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import scala.collection.JavaConverters.seqAsJavaListConverter
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import org.slf4j.LoggerFactory
import com.twitter.hbc.ClientBuilder
import com.twitter.hbc.core.Constants
import com.twitter.hbc.core.HttpHosts
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint
import com.twitter.hbc.core.event.Event
import com.twitter.hbc.core.processor.StringDelimitedProcessor
import com.twitter.hbc.httpclient.auth.Authentication
import com.twitter.hbc.httpclient.auth.OAuth1
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import twitter4j.TwitterStreamFactory
import twitter4j.conf.Configuration
import twitter4j.conf.ConfigurationBuilder
import twitter4j.FilterQuery
import java.text.SimpleDateFormat
import java.util.Date
import com.ifodor.CommandLineParser

object TwitterScraperApp {

  val log = LoggerFactory.getLogger(this.getClass)
  val actorSystem = ActorSystem("tweets")

  def main(args: Array[String]) {
    val config: Config = parseArgs(args) match {
      case Some(config) => config
      case None => {
        actorSystem.terminate()
        return
      }
    }

    val tweetWriter = actorSystem.actorOf(Props(classOf[TweetFileWriter], config.file))
    val twitterConfiguration = new ConfigurationBuilder().setOAuthConsumerKey(config.consumerKey).setOAuthConsumerSecret(config.consumerSecret)
      .setOAuthAccessToken(config.accessToken).setOAuthAccessTokenSecret(config.accessTokenSecret).setJSONStoreEnabled(true).build();
    val twitterStream = new TwitterStreamFactory(twitterConfiguration).getInstance()
    val filter = new FilterQuery().track(config.searchTerms: _*);
    twitterStream.addListener(new TweetStatusListener(tweetWriter))
    twitterStream.filter(filter)

    Runtime.getRuntime.addShutdownHook(new Thread {
      override def run() = {
        twitterStream.shutdown()
        println("\nBye")
        Await.result(actorSystem.terminate(), 1 seconds)
      }
    })
  }

  def parseArgs(args: Array[String]): Option[Config] = {
    val defaultFile = "./" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()) + "_tweets.txt"
    new CommandLineParser().parse(args,
      Config(consumerKey = System.getenv(CommandLineParser.consumerKeySysProp), consumerSecret = System.getenv(CommandLineParser.consumerSecretSysPprop), accessToken = System.getenv(CommandLineParser.accessTokenSysProp), accessTokenSecret = System.getenv(CommandLineParser.accessTokenSecretSysProp), file = defaultFile, searchTerms = List("twitter")))
  }

}

