package ru.evotor

import android.os.Bundle
import android.os.Parcel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * STDEV-26459: on Android 13+ Parcel uses length-prefixed values ([useLenEncoding] == true).
 * The length prefix must count only the payload, not the 4-byte length field itself,
 * otherwise `Parcel.readLazyValue` skips too far and throws BadParcelableException.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33])
class BundlesLengthPrefixTest {
    /** Mirrors the private `useLenEncoding` detection in Bundles.kt. */
    private fun isLenEncodingEnabled(): Boolean {
        val parcel = Parcel.obtain()
        return try {
            parcel.writeValue(emptyMap<String, String>())
            parcel.dataPosition() == 12
        } finally {
            parcel.recycle()
        }
    }

    @Test
    fun `length prefixed values survive sanitize round-trip`() {
        // Given: length-prefixed Parcel encoding is active (Android 13+)
        assertTrue("test requires length-prefixed Parcel encoding", isLenEncodingEnabled())

        val source = Bundle().apply {
            putString("key", "value")
            putStringArrayList("list", arrayListOf("a", "b", "c"))
        }

        // When: the bundle goes through the sanitize (writeTyped) path
        val sanitized = source.sanitizeOutput()
        assertNotNull(sanitized)

        // Then: the sanitized bundle can be written and read back without misalignment
        val parcel = Parcel.obtain()
        try {
            sanitized!!.writeToParcel(parcel, 0)
            parcel.setDataPosition(0)
            val restored = Bundle().apply { readFromParcel(parcel) }
            assertEquals("value", restored.getString("key"))
            assertEquals(listOf("a", "b", "c"), restored.getStringArrayList("list"))
        } finally {
            parcel.recycle()
        }
    }
}
