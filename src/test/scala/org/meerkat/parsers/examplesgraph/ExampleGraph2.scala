package org.meerkat.parsers.examplesgraph

import org.meerkat.Syntax._
import org.meerkat.parsers.Parsers._
import org.meerkat.parsers._
import org.meerkat.parsers.examples._

import scalax.collection.Graph
import scalax.collection.edge.Implicits._

/**
  * Created by sofysmo on 27.11.16.
  */
object ExampleGraph2 {
  val toStr: String => String = x => x
  val A = syn { "ab" ^ toStr }
  val B = syn { "d" ^ toStr }

  val AB: SequenceBuilder[String~String] = A ~~ B
  val S = syn ( A ~~ B  )
  val g = Graph((0~+>1)('a'), (0~+>3)('d'),
    (1~+>2)('b'), (3~+>2)('a'),
    (2~+>4)('d'),(4~+>2)('d'),
    (2~+>3)('b'))
  def main(args: Array[String]): Unit = {
      getResult(S, g, "myGraph2")
  }

}
