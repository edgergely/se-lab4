package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void constructor_SetsTorpedoCount() {
        TorpedoStore store = new TorpedoStore(5);

        assertEquals(5, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void isEmpty_NoTorpedoes_True() {
        TorpedoStore store = new TorpedoStore(0);

        assertTrue(store.isEmpty());
    }

    @Test
    void fire_Success_DecreasesTorpedoCount() {
        TorpedoStore store = new TorpedoStore(5, 0.0);

        boolean result = store.fire(2);

        assertTrue(result);
        assertEquals(3, store.getTorpedoCount());
    }

    @Test
    void fire_LastTorpedo_StoreBecomesEmpty() {
        TorpedoStore store = new TorpedoStore(1, 0.0);

        store.fire(1);

        assertEquals(0, store.getTorpedoCount());
        assertTrue(store.isEmpty());
    }

    @Test
    void fire_AlwaysFailing_ReturnsFalseAndKeepsTorpedoes() {
        TorpedoStore store = new TorpedoStore(3, 1.0);

        boolean result = store.fire(1);

        assertFalse(result);
        assertEquals(3, store.getTorpedoCount());
    }

    @Test
    void fire_ZeroTorpedoes_ThrowsException() {
        TorpedoStore store = new TorpedoStore(3, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    }

    @Test
    void fire_MoreThanAvailable_ThrowsException() {
        TorpedoStore store = new TorpedoStore(2, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(3));
    }

    @Test
    void fire_EmptyStore_ThrowsException() {
        TorpedoStore store = new TorpedoStore(0, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(1));
    }
}