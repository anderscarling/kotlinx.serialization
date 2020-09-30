/*
 * Copyright 2017-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

package kotlinx.serialization.hocon

import com.typesafe.config.ConfigFactory
import kotlinx.serialization.Serializable
import org.junit.Assert.assertEquals
import org.junit.Test

private const val CONFIG_FOR_ROOT_MAP = """
key1 {
    a = "text1"
    b = 11
    }

key2 {
    a = "text2"
    b = 12
}

key3 {
    a = "text3"
    b = 13
}
"""


class HoconRootMapTest {
    @Serializable
    data class CompositeValue(
            val a: String,
            val b: Int
    )

    @Test
    fun `config with root map`() {
        val config = ConfigFactory.parseString(CONFIG_FOR_ROOT_MAP)
        val hocon = Hocon {}
        val obj = hocon.decodeFromConfig<Map<String, CompositeValue>>(config)

        assertEquals(CompositeValue("text1", 11), obj["key1"])
        assertEquals(CompositeValue("text2", 12), obj["key2"])
        assertEquals(CompositeValue("text3", 13), obj["key3"])
    }
}
