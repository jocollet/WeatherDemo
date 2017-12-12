package co.jco.weatherdemo

import co.jco.weatherdemo.JavaStringUtils.returnStringNonNull
import co.jco.weatherdemo.JavaStringUtils.returnStringNullable
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Assert
import org.junit.Test
import java.util.*
import kotlin.properties.Delegates

class Workshop3 {

    // region first test

    /**
     * My first Kotlin unit test
     */
    @Test
    fun additionIsCorrect() {
        Assert.assertEquals(4, 2 + 2)
    }

    // endregion first test

    // region exception handling

    private fun omg() {
        throw IllegalArgumentException("omg")
    }

    /**
     * How to expect an Exception in Kotlin
     */
    @Test(expected = IllegalArgumentException::class)
    fun testException() {
        omg()
    }

    // endregion exception handling

    // region collection operations

    /**
     * How to apply operations to collections
     */
    @Test
    fun testLitterals() {
        val list = 1..10
        val (even, odd) = list.partition { it % 2 == 0 }
        println(even)
        println(odd)
        val result = odd.sortedByDescending { it }
                .subList(0, 3)
                .map { it + 1 }
                .last()

        Assert.assertEquals(123456789, result)
    }
    // endregion collection operations

    // region clone ez

    /**
     * User data class
     */
    data class User(val firstName: String, var middleName: String? = null, val lastName: String, val age: Int)

    /**
     * How to copy (deep clone) data class instances
     */
    @Test
    fun testCopy() {
        val john = User("John", null, "Smith", 42)
        val jack = john.copy(firstName = "Jack", age = 23)

        val clarks: ArrayList<User> = ArrayList()

        arrayOf(john, jack).mapTo(clarks) { it.copy(lastName = "Clark") }
        assertEquals(User("", null, "", 0), clarks.filter { it.age > 27 }.first())
    }
    // endregion clone ez

    // region range check

    /**
     * Range check
     */
    @Test
    fun testRange() {
        assertTrue (10.5 in 0..10)
    }

    // endregion range check

    // region vetoable property

    var bitcoinPrice: Double by Delegates.vetoable(0.0) { prop, old, new ->
        validateStrictGrowth(old, new)
    }

    /**
     * Check that new value is strictly superior to previous one
     */
    private fun validateStrictGrowth(old: Double, new: Double): Boolean {
        return new > old
    }

    @Test
    fun testVeto() {
        bitcoinPrice = 4000.0
        assertEquals(4000.0, bitcoinPrice)
        bitcoinPrice = 2.0
        assertEquals(4000.0, bitcoinPrice)
        bitcoinPrice = 12000.0
        assertEquals(12000.0, bitcoinPrice)
    }

    // endregion vetoable property

    // region annotations

    // @Nullable
    // public static String returnStringNullable() { return ""; }

    // @NonNull
    // public static String returnStringNonNull() { return null; }

    @Test
    fun testNullable() {
        val nullable : String? = returnStringNullable()
        val nonNull : String = returnStringNonNull()
    }

    // endregion annotations

}