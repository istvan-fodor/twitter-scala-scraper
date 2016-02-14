package com.ifodor.twitterscraper

import scala.BigInt
import scala.math.BigInt.int2bigInt

import org.slf4j.LoggerFactory

import akka.actor.ActorRef
import akka.actor.actorRef2Scala
import twitter4j.StallWarning
import twitter4j.Status
import twitter4j.StatusDeletionNotice
import twitter4j.StatusListener
import twitter4j.TwitterObjectFactory

class TweetStatusListener(target: ActorRef) extends StatusListener {

  val log = LoggerFactory.getLogger(classOf[TweetStatusListener])
  var counter = BigInt(0)

  def onStatus(status: Status): Unit = {
    target ! new TweetMessage(TwitterObjectFactory.getRawJSON(status))
    counter += 1
    print(s"\rReceived tweet $counter")
  }

  def onDeletionNotice(delete: StatusDeletionNotice): Unit = {
    println()
    log.info("Delete:", delete)
  }

  def onException(e: Exception): Unit = {
    println()
    log.error("An exception occured:", e)
  }

  def onStallWarning(stallWarning: StallWarning): Unit = {
    println()
    log.info("onStallWarning: {}", stallWarning)
  }

  def onTrackLimitationNotice(numberOfLimitedStatuses: Int): Unit = {
    println()
    log.warn("onTrackLimitationNotice: {}", numberOfLimitedStatuses)
    log.warn("Your search terms are defined too broad and Twitter is limiting your tweet bandwith. Try searching with something more specific.")

  }

  def onScrubGeo(userId: Long, upToStatusId: Long): Unit = {
    println()
    log.info("onScrubGeo: {} {}", userId, upToStatusId)
  }
}