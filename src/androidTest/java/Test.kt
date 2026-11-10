import android.os.Bundle
import android.os.Parcel
import androidx.test.ext.junit.runners.AndroidJUnit4
import ru.evotor.IBundlable
import ru.evotor.devices.commons.printer.PrinterDocument
import ru.evotor.devices.commons.printer.printable.PrintableText
import ru.evotor.sanitizeInput
import ru.evotor.sanitizeOutput
import org.junit.Test
import org.junit.runner.RunWith
import java.math.BigDecimal

@RunWith(AndroidJUnit4::class)
class Test {
    @Test
    fun test2() {
        val source = Bundle().apply {
            putString("key 1", "value 1")
            putSerializable("key 2", BigDecimal("123.456"))
            putParcelable("key 3", PrinterDocument(PrintableText("hello world")))
            putBundle(
                "key 4",
                Bundle().apply {
                    putString("key 5", "value 2.1")
                    putParcelableArrayList("key 6", arrayListOf(PrinterDocument(PrintableText("@@@")), PrinterDocument(PrintableText("!!!"))))
                }
            )
        }

        val sanitized = source.sanitizeOutput()

        val p = Parcel.obtain()
        try {
            sanitized?.writeToParcel(p, 0)
            p.setDataPosition(0)
            val b = Bundle().apply {
                readFromParcel(p)
            }.sanitizeInput()
            b?.classLoader = IBundlable::class.java.classLoader
            println(b)
        } finally {
            p.recycle()
        }

        println(sanitized)
    }

    @Test
    fun test() {
        val sanitized = Bundle().apply {
        }.sanitizeOutput()
        println(sanitized)
    }
}
