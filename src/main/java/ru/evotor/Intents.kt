package ru.evotor

import android.content.Intent
import android.os.Bundle

fun Intent.sanitizeInput(): Intent {
    extras?.let { bundle ->
        replaceExtras(bundle.sanitizeInput() ?: Bundle())
    }
    return this
}

fun Intent.sanitizeOutput(): Intent {
    extras?.let { bundle ->
        replaceExtras(bundle.sanitizeOutput() ?: Bundle())
    }
    return this
}
