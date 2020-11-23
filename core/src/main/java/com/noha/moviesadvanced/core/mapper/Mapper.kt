package com.noha.moviesadvanced.core.mapper

import java.util.ArrayList
import javax.inject.Inject


/**
 *@author: Abdel-Rahman El-Shikh on 08-May-20.
 */
abstract class Mapper<INPUT, OUTPUT>  {
    abstract fun map(input: INPUT): OUTPUT
    fun map(inList: List<INPUT>): List<OUTPUT> {
        val outList: ArrayList<OUTPUT> = ArrayList()
        for (input in inList)
            outList.add(map(input))
        return outList
    }
}