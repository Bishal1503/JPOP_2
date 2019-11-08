package com.JPoP2.controller;

import com.JPoP2.entity.Book;
import com.JPoP2.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookRestControllerTest {
    private static final ObjectMapper om = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    private static Book book;
    private static JSONObject bookJsonObj;

    @Before
    public void initialize() throws JSONException {
        book = new Book();
        book.setId(101L);
        book.setName("The Forest of Enchantments");
        book.setAuthor("Chitra Banerjee Divakaruni");
        book.setCategory("Fiction");
        book.setPublisher("Harper Collins");
        book.setPrice(358.0);

        bookJsonObj = new JSONObject();
        bookJsonObj.accumulate("id", 101);
        bookJsonObj.accumulate("name", "The Forest of Enchantments");
        bookJsonObj.accumulate("author", "Chitra Banerjee Divakaruni");
        bookJsonObj.accumulate("category", "Fiction");
        bookJsonObj.accumulate("publisher", "Harper Collins");
        bookJsonObj.accumulate("price", 358.0);
    }

    @Test
    public void testAddBook() throws Exception {
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(book);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/books").content(bookJsonObj.toString())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andReturn();

        JSONAssert.assertEquals(bookJsonObj.toString(), result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> booksList = new ArrayList<>();
        booksList.add(book);

        JSONArray booksJson = new JSONArray();
        booksJson.put(bookJsonObj);

        Mockito.when(bookRepository.findAll()).thenReturn(booksList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/books").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

        JSONAssert.assertEquals(booksJson.toString(), result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testGetBookFound() throws Exception {
        Optional<Book> bookObj = Optional.of(book);

        Mockito.when(bookRepository.findById(Mockito.any())).thenReturn(bookObj);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/books/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

        JSONAssert.assertEquals(bookJsonObj.toString(), result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testGetBookNotFound() throws Exception {
        Optional<Book> book = Optional.empty();

        Mockito.when(bookRepository.findById(Mockito.any())).thenReturn(book);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/books/2").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isNotFound()).andReturn();

        assertEquals("", result.getResponse().getContentAsString());
    }

    @Test
    public void testDeleteBookByIdFound() throws Exception {
        Mockito.when(bookRepository.existsById(Mockito.any())).thenReturn(true);
        Mockito.doNothing().when(bookRepository).deleteById(Mockito.any());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/books/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

        assertEquals("1", result.getResponse().getContentAsString());
    }

    @Test
    public void testDeleteBookByIdNotFound() throws Exception {
        Mockito.when(bookRepository.existsById(Mockito.any())).thenReturn(false);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/books/2").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isNotFound()).andReturn();

        assertEquals("", result.getResponse().getContentAsString());
    }

    @Test
    public void testUpdateBookByIdFound() throws Exception {
        Mockito.when(bookRepository.existsById(Mockito.any())).thenReturn(true);
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(book);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/books/1").content(bookJsonObj.toString())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

        JSONAssert.assertEquals(bookJsonObj.toString(), result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testUpdateBookByIdNotFound() throws Exception {
        Mockito.when(bookRepository.existsById(Mockito.any())).thenReturn(false);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/books/2").content(bookJsonObj.toString())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isNotFound()).andReturn();

        assertEquals("", result.getResponse().getContentAsString());
    }
}
