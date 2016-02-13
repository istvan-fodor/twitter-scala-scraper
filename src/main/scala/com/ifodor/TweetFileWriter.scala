package com.ifodor

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

class TweetFileWriter(filename: String, searchTerms: List[String]) extends Actor with ActorLogging {

  val file = new File(filename)
  file.getParentFile.mkdirs
  val fos = new FileOutputStream(file, true)
  val channel = fos.getChannel
  //file.getParentFile.mkdirs
  //val writer = new FileWriter(file, true);

  def receive = {
    case TweetMessage(status) => {
      channel.write(ByteBuffer.wrap(status.getBytes))
      channel.write(ByteBuffer.wrap("\n".getBytes))
      //writer.append(status).append("\n")
      //writer.flush();
    }
  }

  override def postStop(): Unit = {
    channel.close()
    fos.close()
    //writer.flush();
    //writer.close();
  }

}