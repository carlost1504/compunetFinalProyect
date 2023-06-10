package com.icesi.backend.UnitTest;

import com.icesi.backend.models.Item;
import com.icesi.backend.models.ItemType;
import com.icesi.backend.respositories.ItemRepository;
import com.icesi.backend.respositories.ItemTypeRepository;
import com.icesi.backend.service.impl.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemTypeRepository itemTypeRepository;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetItem_ValidItemId_ReturnsItemType() {
        // Arrange
        UUID itemId = UUID.randomUUID();
        ItemType itemType = new ItemType();
        when(itemTypeRepository.findById(itemId)).thenReturn(Optional.of(itemType));

        // Act
        ItemType result = itemService.getItem(itemId);

        // Assert
        assertNotNull(result);
        assertEquals(itemType, result);
        verify(itemTypeRepository).findById(itemId);
    }

    @Test
    public void testGetItem_InvalidItemId_ThrowsException() {
        // Arrange
        UUID itemId = UUID.randomUUID();
        when(itemTypeRepository.findById(itemId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> itemService.getItem(itemId));
        verify(itemTypeRepository).findById(itemId);
    }

    @Test
    public void testGetAllItemTypes_ReturnsListOfItemTypes() {
        // Arrange
        List<ItemType> itemTypeList = new ArrayList<>();
        when(itemTypeRepository.findAll()).thenReturn(itemTypeList);

        // Act
        List<ItemType> result = itemService.getAllItemTypes();

        // Assert
        assertNotNull(result);
        assertEquals(itemTypeList, result);
        verify(itemTypeRepository).findAll();
    }

    @Test
    public void testUpdateItem_ValidItemTypeAndId_ReturnsTrue() {
        // Arrange
        UUID itemId = UUID.randomUUID();
        ItemType itemType = new ItemType();
        when(itemTypeRepository.updateNameAndDescriptionAndPriceAndImageByItemTypeId(anyString(), anyString(), anyDouble(), anyString(), eq(itemId))).thenReturn(1);

        // Act
        boolean result = itemService.updateItem(itemType, itemId);

        // Assert
        assertTrue(result);
        verify(itemTypeRepository).updateNameAndDescriptionAndPriceAndImageByItemTypeId(anyString(), anyString(), anyDouble(), anyString(), eq(itemId));
    }

    @Test
    public void testUpdateItem_InvalidId_ThrowsException() {
        // Arrange
        UUID itemId = UUID.randomUUID();
        ItemType itemType = new ItemType();
        when(itemTypeRepository.updateNameAndDescriptionAndPriceAndImageByItemTypeId(anyString(), anyString(), anyDouble(), anyString(), eq(itemId))).thenReturn(0);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> itemService.updateItem(itemType, itemId));
        verify(itemTypeRepository).updateNameAndDescriptionAndPriceAndImageByItemTypeId(anyString(), anyString(), anyDouble(), anyString(), eq(itemId));
    }

    @Test
    public void testCreateItem_ReturnsCreatedItemType() {
        // Arrange
        ItemType itemDTO = new ItemType();
        when(itemTypeRepository.save(itemDTO)).thenReturn(itemDTO);

        // Act
        ItemType result = itemService.createItem(itemDTO);

        // Assert
        assertNotNull(result);
        assertEquals(itemDTO, result);
        verify(itemTypeRepository).save(itemDTO);
    }

    @Test
    public void testAddItemToStock_ValidItemTypeIdAndQuantity_ReturnsListOfItems() {
        // Arrange
        UUID itemTypeId = UUID.randomUUID();
        int quantity = 5;
        ItemType itemType = new ItemType();
        Item item = new Item();
        when(itemTypeRepository.findById(itemTypeId)).thenReturn(Optional.of(itemType));
        when(itemRepository.save(any(Item.class))).thenReturn(item);
        when(itemRepository.findAll()).thenReturn(List.of(item));

        // Act
        List<Item> result = itemService.addItemToStock(itemTypeId, quantity);

        // Assert
        assertNotNull(result);
        assertEquals(List.of(item), result);
        verify(itemTypeRepository).findById(itemTypeId);
        verify(itemRepository, times(quantity)).save(any(Item.class));
        verify(itemRepository).findAll();
    }

    @Test
    public void testAddItemToStock_InvalidItemTypeId_ThrowsException() {
        // Arrange
        UUID itemTypeId = UUID.randomUUID();
        int quantity = 5;
        when(itemTypeRepository.findById(itemTypeId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> itemService.addItemToStock(itemTypeId, quantity));
        verify(itemTypeRepository).findById(itemTypeId);
        verifyNoInteractions(itemRepository);
    }
}

