package com.example
import java.util.NoSuchElementException


case class Till(pricelist : Map[String, BigDecimal]){
	/** Safe to do Option.get , command-line args content has been checked **/
	def calculateSum(groceries : Array[String]): BigDecimal = {

  	  val itemcount = groceries.groupBy(identity).mapValues(_.size)
  	  val goodList = itemcount.keys.forall(pricelist.contains(_))
  	    println(goodList)

  	    if (goodList == false) {
  	    	throw new NoSuchElementException
  	    }

  	  val appleCount = itemcount.get("Apple").get

  	  //Buy One get One free
  	  val applesToPayFor = appleCount - (appleCount/2)

  	  val orangeCount= itemcount.get("Orange").get

  	  //Buy two get One free
  	  val orangesToPayFor = orangeCount - (orangeCount/3)

  	  val appleCost: BigDecimal = applesToPayFor * pricelist.get("Apple").get
  	  val orangeCost: BigDecimal = orangesToPayFor * pricelist.get("Orange").get

  	  orangeCost + appleCost

  }
}


object GrocerBag {
  val grocerbag = """
            G r o c e r B a G
          / r             / r
        /   o           /   o
      /     c         /     c
    G r o c e r B a G       e
    r       r       O       r
    o       B       P       B
    c       a       S       a
    e       G r o c e r B a G
    r     /         C     /  
    B   /           R   /    
    a /             E /      
    G r o c e r B a G  
 
    """


  val itemsOnSale = Map("Apple" -> BigDecimal(60), "Orange" -> BigDecimal(25))

  val NewTill = Till(itemsOnSale)


  def main(args: Array[String]) = {
    println(grocerbag)

    val items = args.forall(itemsOnSale.keys.toList.contains(_))
    items match {
    	case false => {
    		print("Error, only accepted items are: ")
    		itemsOnSale.keys.foreach(item => print(item + " "))
    		sys.exit(0)
    	}
    	case true => {
    		val totalSum = NewTill.calculateSum(args)
    		print("Total cost in pence is: ")
    		println(totalSum)

        }
    }
  }
}

