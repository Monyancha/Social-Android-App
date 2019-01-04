/*
 * Copyright 2019 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.plaidapp.core.data.prefs

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests for [SourcesLocalDataSource] using device Shared Preferences.
 */
class SourcesLocalDataSourceTest {

    private val dnSource = "DN"

    private val sharedPreferences = InstrumentationRegistry.getInstrumentation().context
            .getSharedPreferences("test", Context.MODE_PRIVATE)

    private val dataSource = SourcesLocalDataSource(sharedPreferences)

    @After
    fun tearDown() {
        // cleanup the shared preferences after every test
        sharedPreferences.edit().clear().commit()
    }

    @Test
    fun getSources_returnsNullIfNothingWasAdded() {
        // Given that we haven't saved any source

        // When getting the sources
        val sources = dataSource.getSources()

        // The set of sources is null
        assertNull(sources)
    }

    @Test
    fun addedSource_canBeRetrieved() {
        // Given a source key
        // When adding a source
        dataSource.addSource(dnSource, false)

        // The source can be retrieved
        val sources = dataSource.getSources()
        assertEquals(setOf(dnSource), sources)
        // And the active state is the correct one
        val active = dataSource.getSourceActiveState(dnSource)
        assertFalse(active)
    }

    @Test
    fun addedSources_canBeRetrieved() {
        // Given a set of sources
        val source1 = "DN"
        val source2 = "PH"
        val sources = setOf(source1, source2)

        // When adding sources
        dataSource.addSources(sources, false)

        // Then the sources can be retrieved
        val sourcesSaved = dataSource.getSources()
        assertEquals(sources, sourcesSaved)
        // And the active state is the correct one
        val activeSource1 = dataSource.getSourceActiveState(source1)
        assertFalse(activeSource1)
        val activeSource2 = dataSource.getSourceActiveState(source1)
        assertFalse(activeSource2)
    }

    @Test
    fun updateSource() {
        // Given a source saved
        dataSource.addSource(dnSource, true)

        // When updating the source
        dataSource.updateSource(dnSource, false)

        // Then the source active state was updated
        val active = dataSource.getSourceActiveState(dnSource)
        assertFalse(active)
    }

    @Test
    fun removeSource() {
        // Given a source saved
        dataSource.addSource(dnSource, false)

        // When removing a source
        dataSource.removeSource(dnSource)

        // Then the source can't be retrieved
        val sources = dataSource.getSources()
        assertNotNull(sources)
        assertTrue(sources!!.isEmpty())
        // Then the active state is false
        val active = dataSource.getSourceActiveState(dnSource)
        assertFalse(active)
    }
}
