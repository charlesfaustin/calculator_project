import java.util.NoSuchElementException

import org.scalatest._
import org.scalatest.Assertions._

import com.example._

class HelloSpec extends FlatSpec with Matchers {
  
  val testShoppingList: Array[String] = Array("Apple", "Apple", "Orange", "Apple")
  val itemsOnSale = Map("Apple" -> BigDecimal(60), "Orange" -> BigDecimal(25))
  val testTill = Till(itemsOnSale)
  val testShoppingListCost = testTill.calculateSum(testShoppingList)

  "[Apple, Apple, Orange, Apple]" should "equal 205 pence" in {
    assert(testShoppingListCost  == BigDecimal(205))
  }

  val badShoppingList: Array[String] = Array("Apple", "Biscuit", "Orange", "Apple")

  assertThrows[NoSuchElementException] { 
    testTill.calculateSum(badShoppingList)
  }
}
