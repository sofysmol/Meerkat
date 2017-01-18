package org.meerkat

/**
  * Created by sofysmo on 26.12.16.
  */
object Simple {
  type ParserChar = String => (Boolean, String)
  def parseChar(ch: Char): ParserChar = (str: String) =>  {
    if(str != null && !str.isEmpty)
      if(str.charAt(0) == ch) (true, str.substring(1))
    (false, "Not found symbol "+ch)
  }
  def seq(p1: ParserChar, p2: ParserChar) = (str: String) => {
     val res1 = p1(str)
     if(res1._1) p2(res1._2)
     else (false, res1._2)
  }
  def parserABC = seq(parseChar('A'), seq(parseChar('B'), parseChar('C')))
}
