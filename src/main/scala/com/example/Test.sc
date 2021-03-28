import scala.annotation.tailrec
import scala.collection.immutable.LazyList.cons

val o:Option[Int] = None

o match {
  case Some(value) => value
  case None => "0"
}


val p:Int = o.fold(0)(x=>Integer.parseInt(x.toString))

def sum(l:List[Int]) : Int = l match {
  case Nil => 0
  case ::(head, next) => head +sum(next)
}
var x:List[Int] = 1::1::2::2::4::5::34::Nil
sum(x)


  //List(1,2,3,5,6)



x.foldRight[Double](0)(_+_)
