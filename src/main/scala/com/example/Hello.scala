package com.example


case class Till(pricelist : Map[String, BigDecimal]){
	/** Safe to do Option.get , command-line args content has been checked **/
	def calculateSum(groceries : Array[String]): BigDecimal = {
  	groceries.map(item => pricelist.get(item).get).sum
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

