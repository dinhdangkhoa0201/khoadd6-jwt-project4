package com.example.demo.controllers;

import com.example.demo.entities.ItemEntity;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.utils.GenerateCommon;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ItemControllerTest {

    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemRepository itemRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllSuccess() {
        ItemEntity item = GenerateCommon.createItem();
        List<ItemEntity> listItem = new ArrayList<>();
        listItem.add(item);

        Mockito.when(itemRepository.findAll()).thenReturn(listItem);

        ResponseEntity<List<ItemEntity>> response = itemController.getItems();
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<ItemEntity> listEntity = response.getBody();
        Assert.assertNotNull(listEntity);
        Assert.assertEquals(1, listEntity.size());
    }

    @Test
    public void getItemByIdSuccess() {
        ItemEntity item = GenerateCommon.createItem();
        Mockito.when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        ResponseEntity<ItemEntity> response = itemController.getItemById(1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(item.getId(), response.getBody().getId());
    }

    @Test
    public void getItemByIdFailed() {
        ResponseEntity<ItemEntity> response = itemController.getItemById(1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getItemsByNameSuccess() {
        ItemEntity item = GenerateCommon.createItem();
        List<ItemEntity> listItem = new ArrayList<>();
        listItem.add(item);

        Mockito.when(itemRepository.findByName("New Item")).thenReturn(listItem);

        ResponseEntity<List<ItemEntity>> response = itemController.getItemsByName("New Item");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<ItemEntity> listEntity = response.getBody();
        Assert.assertNotNull(listEntity);
        Assert.assertEquals(1, listEntity.size());
    }

    @Test
    public void getItemsByNameFailed() {
        ResponseEntity<List<ItemEntity>> response = itemController.getItemsByName("New Item");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
