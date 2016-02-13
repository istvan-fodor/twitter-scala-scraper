package com.ifodor

import java.io.FileWriter
import java.nio.file.Paths

import akka.actor.Actor
import akka.actor.ActorLogging
import twitter4j.Status
import twitter4j.TwitterObjectFactory

case class TweetMessage(status : String)

class TweetFileWriter(directory: String, searchTerms: List[String]) extends Actor with ActorLogging {
  
  val filename = searchTerms.foldLeft("")(_ + _).replaceAll("\\W", "")
  Paths.get(directory, "").toFile().mkdirs()
  val file = Paths.get(directory, filename).toFile()
  val writer = new FileWriter(file, true);

  def receive = {
    case TweetMessage(status) => {
      writer.append(status).append("\n")
    }
  }

  override def postStop(): Unit = {
    writer.flush();
    writer.close();
  }

}