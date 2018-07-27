package de.phash.manuel.mathetrainer

class Status private constructor() {

    init {
        println("This ($this) is a singleton")
    }

    private object Holder {
        val INSTANCE = Status()
    }

    companion object {
        val instance: Status by lazy { Holder.INSTANCE }
    }

    internal var aufgabe: Int = 0
    internal var richtig: Int = 0
    internal var versuche: Int = 0
    internal var bisMax: Int = 20


}

