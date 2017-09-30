package org.meerkat.parsers.examplesgraph

/**
  * Created by sofysmo on 05.05.17.
  */

import org.meerkat.Syntax._
import org.meerkat.parsers.Parsers._
import org.meerkat.util.IGraph
import org.meerkat.parsers._

import scalax.collection.Graph
import scalax.collection.edge.Implicits._

object ExampleGraph23NegativeNonterminal {
  val A = syn{"a"}
  val E = syn{"g" ~ not{A}}

  val g = Graph((2~+#>1)("a"),(2~+#>1)("b"),(1~+#>0)("a"))
  def main(args: Array[String]): Unit = {
    getResult(E,IGraph(g), "myGraph23")
  }
}
