package de.phash.manuel.mathetrainer

import org.junit.Assert
import org.junit.Test
import java.util.stream.IntStream

class MatheAufgabenTest {

    @Test
    fun addition(): Unit {
        val aufgabe = MatheAufgaben.getAddition(4, 20)
        Assert.assertEquals(4, aufgabe.loesungen.size)
    }

    @Test
    fun addition_bisMax() {
        val max = 20
        IntStream.range(0, 100).forEach {
            val aufgabe = MatheAufgaben.getAddition(4, max)
            System.out.println(aufgabe.aufgabe)
            Assert.assertTrue("Die Loesungen sind ausserhalb des definierten Bereichs", aufgabe.loesungen.filter { a -> a > max }.isEmpty())
            Assert.assertTrue("Die Loesungen sind ausserhalb des definierten Bereichs", aufgabe.loesungen.filter { a -> a < 0 }.isEmpty())
        }

    }

    @Test
    fun subtraktion(): Unit {
        val aufgabe = MatheAufgaben.getAddition(4, 20)
        Assert.assertEquals(4, aufgabe.loesungen.size)
    }

    @Test
    fun subtraktion_bisMax() {
        val max = 20
        IntStream.range(0, 100).forEach {
            val aufgabe = MatheAufgaben.getSubstraktion(4, max)
            System.out.println(aufgabe.aufgabe)
            Assert.assertTrue("Die Loesungen sind ausserhalb des definierten Bereichs", aufgabe.loesungen.filter { a -> a > max }.isEmpty())
            Assert.assertTrue("Die Loesungen sind ausserhalb des definierten Bereichs", aufgabe.loesungen.filter { a -> a < 0 }.isEmpty())
        }

    }
}