package com.apsoftware.myapplication

import com.apsoftware.myapplication.ui.DefinitionListAdapter
import com.apsoftware.myapplication.viewmodels.DefinitionViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks

class DefinitionViewModelTest {

    private lateinit var sut: DefinitionViewModel

    @Mock
    lateinit var mockListAdapter: DefinitionListAdapter

    @Before
    fun setUp() {
        initMocks(this)
        sut = DefinitionViewModel()
        sut.setMockAdapter(mockListAdapter)
        sut.init()
    }

    @Test
    fun setupDefinitionListAdapterTest() {
        sut.setDefinitionsInAdapter(ArrayList())
        verify(mockListAdapter, times(1)).setDefinitions(ArgumentMatchers.anyList())
        verify(mockListAdapter, times(1)).notifyDataSetChanged()
    }
}