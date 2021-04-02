//#full-example
package com.example


import akka.actor.{Actor, ActorLogging, ActorSystem, Props, TypedActor}
import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import com.example.GreeterMain.SayHello

//#greeter-actor
object Greeter {
  final case class Greet(whom: String, replyTo: ActorRef[Greeted])
  final case class Greeted(whom: String, from: ActorRef[Greet])

  def apply(): Behavior[Greet] = Behaviors.receive { (context, message) =>
    context.log.info("Hello {}!", message.whom)
    //#greeter-send-messages
    message.replyTo ! Greeted(message.whom, context.self)
    //#greeter-send-messages
    Behaviors.same
  }
}
//#greeter-actor


//#greeter-bot
object GreeterBot {

  def apply(max: Int): Behavior[Greeter.Greeted] = {
    bot(0, max)
  }

  private def bot(greetingCounter: Int, max: Int): Behavior[Greeter.Greeted] =
    Behaviors.receive { (context, message) =>
      val n = greetingCounter + 1
      context.log.info("Greeting {} for {}", n, message.whom)
      if (n == max) {
        Behaviors.stopped
      } else {
        message.from ! Greeter.Greet(message.whom, context.self)
        bot(n, max)
      }
    }
}
//#greeter-bot


//todo : calculater
object Calculator {

  def c(a:Int,b:Int):Int = a+b

  final case class Ping(a:Int, b:Int, replyTo:ActorRef[Added])

  final case class Added(result:Int,from:ActorRef[Ping])

  def apply: Behavior[Ping] = Behaviors.receive { (context, message) =>
    context.log.info("Result {} ",message.a+message.b)
    message.replyTo ! Added(c(message.a,message.b),context.self)
    Behaviors.same
  }
}





//todo: Calculator Bot


//#greeter-main
object GreeterMain {

  final case class SayHello(name: String)

  def apply(): Behavior[SayHello] =
    Behaviors.setup { context =>
      //#create-actors
      val greeter = context.spawn(Greeter(), "greeter")
      //#create-actors

      Behaviors.receiveMessage { message =>
        //#create-actors
        val replyTo = context.spawn(GreeterBot(max = 3), message.name)
        //#create-actors
        greeter ! Greeter.Greet(message.name, replyTo)
        Behaviors.same
      }
    }
}
//#greeter-main



case class Adding(a:Int,b:Int)

class  AddingActor extends Actor with ActorLogging{
  override def receive: Receive = {
    case Adding(a,b) => log.info("Result {}",(a+b).toString)
  }
}

object AddingMain {

  final case class SayHello(name: String)

  def apply(): Behavior[SayHello] =
    Behaviors.setup { context =>
      //#create-actors
      val greeter = context.spawn(Greeter(), "greeter")
      //#create-actors

      Behaviors.receiveMessage { message =>
        //#create-actors
        val replyTo = context.spawn(GreeterBot(max = 3), message.name)
        //#create-actors
        greeter ! Greeter.Greet(message.name, replyTo)
        Behaviors.same
      }
    }
}
case class misn(a:Int,b:Int)

class HelloAkka() extends  Actor{
  override def receive: Receive = {
    case a:misn=> print(a.a+a.b + " Intestring scenario ")
    case _ => {
      println("Lord is Merciful")
      val childMe = context.actorOf(Props[GreetingActor],"gals")
      childMe ! "men"
    }



  }

}

class GreetingActor() extends Actor{
  override def receive: Receive = {
    case _ => println("Blame me not")
  }
}

//#main-class
object AkkaQuickstart extends App {


  var actossystem = ActorSystem("ActorSystem")
  var actor = actossystem.actorOf(Props[HelloAkka],"HelloAkka")


  actor ! "bllod"

//  var actSystem = ActorSystem("Beloong")
  //var actsor = actossystem.actorOf(Props[GreetingActor],"GreetenActor")
//
 // actsor ! "men"
  //#actor-system
//  val greeterMain: ActorSystem[GreeterMain.SayHello] = ActorSystem(GreeterMain(), "AkkaQuickStart")
  //#actor-system


  //#main-send-messages
//  greeterMain ! SayHello("miles")
  //#main-send-messages
}
//#main-class
//#full-example
