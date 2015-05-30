package org.meerkat.tmp

object Test {
  
  import Parsers._
  import org.meerkat.tmp.Negation._
  import NonterminalBuilder.syn
//  
//  val E: Nonterminal = ^ ( E ~ "*" ~ E 
//                         | E ~ "+" ~ E 
//                         | A )
//                        
//  val A: Nonterminal = ^ ("a" | "b")
//  
//  def main(args: Array[String]): Unit = {
//    parse("a*a+a", E)
//  }
  
//  val A: Nonterminal = ntSym("A", "a")
//  val B: Nonterminal = ntSeq("B", A.+ ~ "b")
//  
//  def main(args: Array[String]): Unit = {
//    parse("aaab", B)
//  }
  
  val toStr: String => String = x => x
  
  val A = syn { "a".^{ toStr(_) } }
  val B = syn { "b".^{ toStr(_) } }
  
//  val S: SequenceBuilder { type Value = (String,String) } = A ~ B
//  S.^^ { case (s1,s2) => s1 + s2 }
  
  val S = syn { ( A ~ B  ^^ { case (s1,s2) => s"$s2++$s1" } 
                | "c"    .^ { toStr(_) } ) }
  
  val C = syn { "c".^{ toStr(_) } }
  
  val LIST: NonterminalWithAction[String]
  
    = syn ( LIST ~ C    ^^ { case (s1,s2) => s"$s1;$s2" } 
          | C )
          
  val SL: NonterminalWithAction[List[String]] = syn { S.* ^^ { x => x.:+("HoHo!!!") }}
    
  def main(args: Array[String]): Unit = {
    parse("ababab", SL)
  }
  
//  trait LIST[T] {
//    def add(elem: T): LIST[T]
//  }
//  
//  trait Semantic[A, B] {
//    
//    type L = LIST[Value]
//    type Value
//  }
//  
//  
//  
//  trait NoValue
//  implicit def f[A <: NoValue, B <: NoValue] = new Semantic[A, B] { type Value = NoValue }
//  implicit def g[A, B] = new Semantic[A, B] { type Value = (A, B) }
//  
//  def fun[A, B](a: A, b: B)(implicit o: Semantic[A,B]): o.L = ???
//  
//  val a1: Int = ???
//  val a2: Int = ???
//  var v = fun(a1,a2)
//  v = v.add((a1,a2))
  
}