package org.sofysmo

//import org.meerkat.util.visualization
import org.sofysmo.Visualizer
import org.meerkat.Syntax._
import org.meerkat.parsers._
import org.meerkat._
import org.meerkat.util._
import Parsers._
import org.meerkat.sppf._
import javax.swing.JFrame
import org.jgrapht.alg._;
import org.jgrapht._;
import org.jgrapht.graph._
import org.jgrapht.ext._
import java.io.StringReader
import javax.swing.JScrollPane
import java.awt.Color
import java.awt.Dimension
import java.awt.Rectangle
import org.jgrapht.ext.JGraphModelAdapter
import javax.swing.JApplet
import javax.swing.JFrame
import org.jgraph.JGraph
import org.jgraph.graph.DefaultGraphCell
import org.jgraph.graph.GraphConstants

import javax.swing._
import java.awt.event._
 
/*class ShowApplet extends JApplet {
  // 'val' or 'var' both ok; 'val' is ok because although
  // JTextArea changes, the reference to it, 'text' itself, does not.
  val text = new JTextArea()
  var startCount: Int = 0
  val DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
  val DEFAULT_SIZE = new Dimension( 530, 320 );
  
  override def init() {
    val dot = scala.io.Source.fromFile("/home/sofysmo/Documents/Eclipse/Meerkat/graph.dot").mkString
    val vertexProvider = new CustomVertexProvider()
    val edgeProvider = new CustomEdgeProvider()
    val graph = new DefaultDirectedGraph[String, DefaultEdge](classOf[DefaultEdge])
    val imp = new DOTImporter[String, DefaultEdge](vertexProvider, edgeProvider)
    imp.importGraph(graph, new StringReader(dot))
    val m_jgAdapter = new JGraphModelAdapter( graph );
    val jgraph = new JGraph( m_jgAdapter );
    adjustDisplaySettings( jgraph );
    getContentPane(  ).add( jgraph );
        resize( DEFAULT_SIZE );
    //val frame: HelloWorldJGraphX = new HelloWorldJGraphX
    /*setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    getContentPane().add(new JScrollPane(graph));
    setSize(400, 320)
    setVisible(true)*/
  }
  def adjustDisplaySettings(jg: JGraph) = {
        jg.setPreferredSize( DEFAULT_SIZE );

        var  c = DEFAULT_BG_COLOR;
        var colorStr:String = null;

        try {
            colorStr = getParameter( "bgcolor" );
        }
        catch {case e: Exception => println("exception caught: " + e);}

        if( colorStr != null ) {
            c = Color.decode( colorStr );
        }

        jg.setBackground( c );
        
  }
  override def start() {
    text.append("Applet started: " + startCount + "\n")
    startCount = startCount + 1
  }
  override def stop() {
    text.append("Applet stopped.\n")
  }
}
class CustomVertexProvider extends VertexProvider[String]{
    override def buildVertex(label: String, attribute: java.util.Map[String,String]): String = {
    label
  }
}
class CustomEdgeProvider extends EdgeProvider[String, DefaultEdge]{
  val edgeFactory = new ClassBasedEdgeFactory[String, DefaultEdge](classOf[DefaultEdge])
  override def buildEdge(from: String, to: String,
        label: String, attributes: java.util.Map[String, String]): DefaultEdge = {
        edgeFactory.createEdge(from, to)
  }
}*/
object Main extends App {
  val E: Nonterminal = syn ( "(" ~ E ~ ")" 
                           | E ~ "*" ~ E 
                           | E ~ "/" ~ E
                           | E ~ "+" ~ E 
                           | E ~ "-" ~ E
                           |  "-" ~ E
                           |   "a" )                
    val input = Input("a+a-a*a")
    val sppfLookup = new DefaultSPPFLookup(input)
    run(input, sppfLookup, E)
    val root = sppfLookup.getStartNode(E, 0, input.length)
    Visualizer.visualize(Visualizer.toDot(root.get, input),"myGraph",".")
    /*val DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
    val DEFAULT_SIZE = new Dimension( 530, 320 );
    
    override def init() {
    val dot = scala.io.Source.fromFile("graph.dot").mkString
    val vertexProvider = new CustomVertexProvider()
    val edgeProvider = new CustomEdgeProvider()
    val graph = new DefaultDirectedGraph[String, DefaultEdge](classOf[DefaultEdge])
    val imp = new DOTImporter[String, DefaultEdge](vertexProvider, edgeProvider)
    imp.importGraph(graph, new StringReader(dot))
    val m_jgAdapter = new JGraphModelAdapter( graph );
    val jgraph = new JGraph( m_jgAdapter );
    
    adjustDisplaySettings( jgraph );
    getContentPane(  ).add( jgraph );
        resize( DEFAULT_SIZE );
    //val frame: HelloWorldJGraphX = new HelloWorldJGraphX
    /*setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    getContentPane().add(new JScrollPane(graph));
    setSize(400, 320)
    setVisible(true)*/
  }
  def adjustDisplaySettings(jg: JGraph) = {
        jg.setPreferredSize( DEFAULT_SIZE );

        var  c = DEFAULT_BG_COLOR;
        var colorStr:String = null;

        try {
            colorStr = getParameter( "bgcolor" );
        }
        catch {case e: Exception => println("exception caught: " + e);}

        if( colorStr != null ) {
            c = Color.decode( colorStr );
        }

        jg.setBackground( c );
    }    
}  
class CustomVertexProvider extends VertexProvider[String]{
    override def buildVertex(label: String, attribute: java.util.Map[String,String]): String = {
    label
  }
}
class CustomEdgeProvider extends EdgeProvider[String, DefaultEdge]{
  val edgeFactory = new ClassBasedEdgeFactory[String, DefaultEdge](classOf[DefaultEdge])
  override def buildEdge(from: String, to: String,
        label: String, attributes: java.util.Map[String, String]): DefaultEdge = {
        edgeFactory.createEdge(from, to)
  }*/
}

