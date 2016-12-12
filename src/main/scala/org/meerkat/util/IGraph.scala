package org.meerkat.util


/**
  * Created by sofysmo on 04.12.16.
  */
import scalax.collection.Graph
import scalax.collection.edge.{LDiEdge, LkDiEdge}
import scalax.collection.edge.Implicits._
import scalax.collection._

trait IEdge{
  def getFromNode: INode
  def getToNode: INode
  def getLabel: String
}
trait INode{
  def getOutgoingEdges: Set[IEdge]
  def getIncomingEdges: Set[IEdge]
  def isPredecessorOf(n: INode): Boolean
  def value: Int
}

trait IGraph {
  def countNodes: Int
  def countEdges: Int
  def get(n: Int): INode
}
object IGraph{
  def apply(s: Graph[Int,LkDiEdge]) = new SimpleGraph(s)
  implicit def toInput(s: Graph[Int,LkDiEdge]) = Graph(s)
}

class SimpleGraph(val graph: Graph[Int,LkDiEdge]) extends IGraph{
  class Node(val value: Int) extends INode{
    val node = graph.get(value)
    def getOutgoingEdges: Set[IEdge]
      = node.outgoing.map(edge =>
      new Edge(new Node(edge.from.value), new Node(edge.to.value), edge.label.toString))
    def getIncomingEdges: Set[IEdge]
      = node.incoming.map(edge => new Edge(new Node(edge.from.value), new Node(edge.to.value), edge.label.toString))
    def isPredecessorOf(n: INode): Boolean
      = node isPredecessorOf graph.get(n.value)

  }
  class Edge(from: Node, to: Node, label: String) extends IEdge{
    def getFromNode: INode = from
    def getToNode: INode = to
    def getLabel: String = label
  }
  def countNodes: Int = graph.order
  def countEdges: Int = graph.size
  def get(n: Int): INode = new Node(n)
}
