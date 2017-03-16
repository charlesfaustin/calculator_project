import java.util.NoSuchElementException

import org.scalatest._
import org.scalatest.Assertions._

import com.example._

class HelloSpec extends FlatSpec with Matchers {
  
  val testShoppingList: Array[String] = Array("Apple", "Apple", "Orange", "Apple")
  val itemsOnSale = Map("Apple" -> BigDecimal(60), "Orange" -> BigDecimal(25))
  
  val testTill = Till(itemsOnSale)
  val testShoppingListCost = testTill.calculateSum(testShoppingList)

  val longerShoppingList: Array[String] = Array("Apple", "Apple", "Orange", "Apple", "Orange","Orange","Orange")
  val longerShoppingListCost =  testTill.calculateSum(longerShoppingList)

  "[Apple, Apple, Orange, Apple]" should "equal 145 pence" in {
    assert(testShoppingListCost  == BigDecimal(145))
  }

  "[Apple, Apple, Orange, Apple, Orange,Orange,Orange]" should "equal 195 pence" in {
    assert(longerShoppingListCost  == BigDecimal(195))
  }

  val badShoppingList: Array[String] = Array("Apple", "Biscuit", "Orange", "Apple")

  assertThrows[NoSuchElementException] { 
    testTill.calculateSum(badShoppingList)
  }
}
