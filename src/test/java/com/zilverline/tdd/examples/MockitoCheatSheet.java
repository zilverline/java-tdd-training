package com.zilverline.tdd.examples;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class MockitoCheatSheet {

    @Test
    public void verify_interactions() {
        // Create a mock object implementing the specified interface.
        List<String> mockedList = mock(List.class);

        // Call some methods on the mock.
        mockedList.add("abc");
        mockedList.clear();

        // Verify the interactions
        verify(mockedList).add("abc");
        verify(mockedList, times(1)).clear();
        verify(mockedList, never()).contains(isA(String.class));
    }

    @Test
    public void stub_method_calls() {
        // Create a mock object implementing the specified interface.
        List<String> mockedList = mock(List.class);

        // Stub calls before executing the system under test
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.add(isA(String.class))).thenThrow(new UnsupportedOperationException());

        // Use the mock (normally in real code, not test code!).
        assertEquals("first", mockedList.get(0));

        // Mockito has sensible defaults for return value (0, false, null, empty collection, etc)
        assertFalse(mockedList.contains("hello"));

        ArgumentCaptor<String> captureContainsArgument = ArgumentCaptor.forClass(String.class);
        verify(mockedList).contains(captureContainsArgument.capture());
        assertEquals("hello", captureContainsArgument.getValue());
    }
}
