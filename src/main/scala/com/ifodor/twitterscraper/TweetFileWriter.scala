package com.ifodor.twitterscraper

import java.io.FileWriter
import java.nio.file.Paths
import akka.actor.Actor
import akka.actor.ActorLogging
import twitter4j.Status
import twitter4j.TwitterObjectFactory
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer

case class TweetMessage(status: String)

class TweetFileWriter(filename: String) extends Actor with ActorLogging {

  val file = new File(filename)
  new File(file.getAbsolutePath).getParentFile.mkdirs
  val fos = new FileOutputStream(file, true)
  val channel = fos.getChannel

  def receive = {
    case TweetMessage(status) => {
      channel.write(ByteBuffer.wrap(status.getBytes))
      channel.write(ByteBuffer.wrap("\n".getBytes))
    }
  }

  override def postStop(): Unit = {
    channel.close()
    fos.close()
  }

}