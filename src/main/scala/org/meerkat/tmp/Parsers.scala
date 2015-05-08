package org.meerkat.tmp

import org.meerkat.sppf.NonPackedNode
import org.meerkat.util.Input
import org.meerkat.sppf.SPPFLookup
import scala.reflect.ClassTag
import org.meerkat.sppf.DefaultSPPFLookup
import org.meerkat.util.Visualization

object Parsers {
  
  import AbstractCPSParsers._
  
  implicit object obj1 extends Composable[NonPackedNode, NonPackedNode] {
    type R = NonPackedNode
    
    type Sequence = Parsers.Sequence
    
    def sequence(f: (Input, Int, SPPFLookup) => Result[NonPackedNode]): Sequence 
      = new Parsers.Sequence { def apply(input: Input, i: Int, sppfLookup: SPPFLookup) = f(input, i, sppfLookup) } 
    
    def index(a: NonPackedNode): Int = a.rightExtent
    def intermediate(a: NonPackedNode, b: NonPackedNode, p: AbstractParser[NonPackedNode], sppfLookup: SPPFLookup): NonPackedNode 
      = sppfLookup.getIntermediateNode(p, a, b) 
  }
  
  implicit object obj2 extends Alternative[NonPackedNode, NonPackedNode] {
    type Alternation = Parsers.Alternation
    
    def alternation(f: (Input, Int, SPPFLookup) => Result[NonPackedNode]): Alternation
      = new Alternation { def apply(input: Input, i: Int, sppfLookup: SPPFLookup) = f(input, i, sppfLookup) }
    
    def result(e: NonPackedNode, p: AbstractParser[NonPackedNode], nt: AbstractParser[Any], sppfLookup: SPPFLookup): NonPackedNode
      = sppfLookup.getNonterminalNode(nt, p, e)
  }
  
  implicit object obj3 extends Memoizable[NonPackedNode] {
    type U = Int
    def value(t: NonPackedNode): Int = t.rightExtent
  }
  
  implicit object obj4 extends CanBecomeNonterminal[NonPackedNode] {
    type Nonterminal = Parsers.Nonterminal
    def nonterminal(p: (Input, Int, SPPFLookup) => Result[NonPackedNode]): Nonterminal 
      = new Nonterminal { def apply(input: Input, i: Int, sppfLookup: SPPFLookup) = p(input, i, sppfLookup) }
  }
  
  trait HasSequenceOp extends AbstractParser[NonPackedNode] {
    def ~ (p: Symbol): Sequence = AbstractParser.seq(this, p)
  }
  
  trait HasAlternationOp extends AbstractParser[NonPackedNode] {
    def | (p: Sequence): Alternation = AbstractParser.alt(this, p)
    def | (p: Symbol): Alternation = AbstractParser.alt(this, p)
  }
  
  trait Sequence extends HasSequenceOp with HasAlternationOp { 
    override def isSequence = true
  }
  
  trait Alternation extends HasAlternationOp { 
    override def isAlternation = true
  }
  
  trait Symbol extends HasSequenceOp with HasAlternationOp {
    override def isSymbol = true
  }
  
  trait Nonterminal extends Symbol { 
    override def isNonterminal = true 
  }
  
  trait Terminal extends Symbol { 
    override def isTerminal = true 
  }
  
  def nt(name: String)(p: => AbstractParser[NonPackedNode]): Nonterminal
    = memoize(p, name)
    
  implicit def terminal(s: String): Terminal 
    = new Terminal { 
        def apply(input: Input, i: Int, sppfLookup: SPPFLookup) = {
          if (input.startsWith(s, i))
            CPSResult.success(sppfLookup.getTerminalNode(s, i))
          else CPSResult.failure
        } 
      }
  
  def run(input: Input, sppf: SPPFLookup, parser: Nonterminal): Unit = {
    parser(input, 0, sppf)(t => if(t.rightExtent == input.length) { println(s"Success: $t")  })
    Trampoline.run
  }
  
  def parse(sentence: String, parser: Nonterminal): Unit = {
    val input = new Input(sentence)
    val sppf = new DefaultSPPFLookup(input)
    
    run(input, sppf, parser)
    
    val startSymbol = sppf.getStartNode(parser.name, 0, sentence.length())
    
    startSymbol match {
      case None       => println("Parse error")
      case Some(node) => println("Success: " + node)
                         println(sppf.countAmbiguousNodes + ", " + sppf.countIntermediateNodes + ", " + sppf.countPackedNodes + ", " + sppf.countNonterminalNodes + ", " + sppf.countTerminalNodes)
                         println("Visualizing...") 
                         Visualization.toDot(startSymbol.get)
                         println("Done!")
    }
  }
  
}