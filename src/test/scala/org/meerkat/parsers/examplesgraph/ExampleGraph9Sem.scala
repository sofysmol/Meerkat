package org.meerkat.parsers.examplesgraph

import org.meerkat.Syntax._
import org.meerkat.parsers._
import Parsers._
import OperatorParsers._
import org.meerkat.util.InputGraph

import scalax.collection.Graph
import scalax.collection.edge.Implicits._
/**
  * Created by sofysmo on 28.11.16.
  */
/*object ExampleGraph9Sem {
  val toInt: String => Int = x => x.toInt
  implicit val Layout = layout { """\s?""".r }



  val E: OperatorNonterminal & Exp =
    syn (  right { E ~ "^" ~ E } & { case x~y => Pow(x, y) }
      |> "-" ~ E               & { Neg(_) }
      |> left ( E ~ "*" ~ E    & { case x~y => Mul(x, y) }
      |         E ~ "/" ~ E    & { case x~y => Div(x, y) })
      |> left ( E ~ "+" ~ E    & { case x~y => Add(x, y) }
      |         E ~ "-" ~ E    & { case x~y => Sub(x, y) })
      | "(" ~ E ~ ")"
      | Int                    ^ { s => Num(toInt(s)) }
    )
  val g = Graph((0~+>1)('1'))//,(1~+>2)("+"),(2~+>3)("2"),(3~+>4)("*"),(4~+>5)("3"),(5~+>6)("-"),(6~+>7)("5"))
  def main(args: Array[String]): Unit = {
    //getResult(E($), g)
    val result = exec(E($), new InputGraph(g))//"1+2*3-5")
    println(result)
    //assert(result.isSuccess)
  }

}*/
