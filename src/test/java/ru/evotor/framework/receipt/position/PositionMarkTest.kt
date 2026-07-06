package ru.evotor.framework.receipt.position

import junit.framework.TestCase.assertNull
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.evotor.framework.receipt.Measure
import ru.evotor.framework.receipt.Position
import java.math.BigDecimal


class PositionMarkTest {

    lateinit var testPositionBuilder: Position.Builder

    @Before
    fun before() {
        testPositionBuilder = Position.Builder.newInstance(
            "",
            "",
            "",
            Measure("шт", 0, 0),
            BigDecimal(50.00),
            BigDecimal(1)
        )
    }

    @Test
    fun `setMarkTest with null mark`() {
        val mark: Mark? = null
        val position = testPositionBuilder.setMark(mark)?.build()
        assertNull(position?.mark)
    }

    @Test
    fun `setMarkTest with invalid RawMark`() {
        val mark = Mark.RawMark("")
        val position = testPositionBuilder.setMark(mark)?.build()
        assertNull(position?.mark)
    }

    @Test
    fun `setMarkTest with valid RawMark`() {
        val mark = Mark.MarkByFiscalTags(VALID_MARK)
        val position = testPositionBuilder.setMark(mark)?.build()
        Assert.assertEquals(mark, position?.mark)
    }

    @Test
    fun `setMarkTest with invalid MarkByFiscalTags`() {
        val mark = Mark.MarkByFiscalTags("")
        val position = testPositionBuilder.setMark(mark)?.build()
        assertNull(position?.mark)
    }

    @Test
    fun `setMarkTest with valid MarkByFiscalTags`() {
        val mark = Mark.MarkByFiscalTags(VALID_MARK)
        val position = testPositionBuilder.setMark(mark)?.build()
        Assert.assertEquals(mark, position?.mark)
    }

    companion object {
        const val VALID_MARK = "010464007801637221AgqLybqxM9MbR 91FFD0 92dGVzdL31KAYL0YT6592MjmW7a2HkF3IY+muf2pVSKdQ="
    }
}
