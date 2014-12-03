package gof.behavior.chainofresponsibility.functionchain

case class Request(value: Int)

object ChainController {
  type Handler = PartialFunction[Request, Unit]

  val handleEvens: Handler = {
    case Request(x) if x % 2 == 0 => println("Handling even value " + x)
  }

  val handleOdds: Handler = {
    case Request(x) if x % 2 == 1 => println("Handling odd value " + x)
  }

  val handlePalindrome: Handler = {
    case Request(x) if x.toString.reverse.toString == x.toString =>
      println("Handling palindrome " + x)
  }

  val handlerChain = handlePalindrome orElse handleEvens orElse handleOdds
}


object Example extends App {
  ChainController.handlerChain(Request(21))
  ChainController.handlerChain(Request(121))
  ChainController.handlerChain(Request(12))
}