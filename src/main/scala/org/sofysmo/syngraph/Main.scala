package org.sofysmo.syngraph

import scalax.collection.GraphPredef._, scalax.collection.GraphEdge._
import scalax.collection.Graph
import scalax.collection.edge._
import scalax.collection._
import org.meerkat.Syntax._
import org.meerkat.parsers._
//import org.jgrapht._;
//import org.jgrapht.graph._
import Parsers._

object Main {
  val A = syn { "a" }
  val B = syn { "b" } 
  val S = 
  syn ( A ~ B 
      | "c" 
      )
      
  def main(args: Array[String]):Unit = {
    /*val graph = new DefaultDirectedGraph[String, DefaultEdge](classOf[DefaultEdge])
    graph.addVertex("a")
    graph.addVertex("b")
    graph.addEdge("a", "b")
    graph.get*/
    case class MyLabel(i: String)
    //val g = Graph()
    
    val e1 = LDiHyperEdge(1,2)(MyLabel("a"))
    val e2 = LDiHyperEdge(2,3)(MyLabel(" "))
    val e3 = LDiHyperEdge(3,4)(MyLabel("b"))
    val g = Graph(e1, e2, e3)
    val g1 = Graph(e1)
    import scalax.collection.edge.LBase.LEdgeImplicits
    object MyImplicit extends LEdgeImplicits[MyLabel]; import MyImplicit._
    val t = g.nodes.get(1).outgoing
    //val result = exec(S, "a b")
  }
}