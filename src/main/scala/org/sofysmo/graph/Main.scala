package org.sofysmo.graph
import org.graphstream.graph._;
import org.graphstream.graph.implementations._;

object Main {
  def main(args: Array[String]): Unit  = {
    val graph = new SingleGraph("Tutorial 1");

		graph.addNode("A");
		graph.addNode("B");
		graph.addNode("C");
		graph.addEdge("AB", "A", "B");
		graph.addEdge("BC", "B", "C");
		graph.addEdge("CA", "C", "A");

		graph.display();
  }
}