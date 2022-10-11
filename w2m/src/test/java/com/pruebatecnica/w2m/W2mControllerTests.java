package com.pruebatecnica.w2m;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebatecnica.w2m.hero.Hero;
import com.pruebatecnica.w2m.hero.HeroController;
import com.pruebatecnica.w2m.hero.HeroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
class W2mControllerTests {

    @Mock
    private HeroService service;

    @InjectMocks
    private HeroController controller;

    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    void whenValidIdthenReturns200() throws Exception {
        given(service.getHeroById(1L))
                .willReturn(new Hero("Superman"));
        MockHttpServletResponse response =
                mockMvc.perform(
                        get("/heroes/1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertThat(response.getContentAsString()).isEqualTo(
                objectMapper.writeValueAsString(new Hero("Superman")));
    }


    @Test
    void whenCreatingHero_thenReturns201() throws Exception {
        MockHttpServletResponse response =
                mockMvc.perform(
                                post("/heroes/")
                                        .accept(MediaType.APPLICATION_JSON)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(new Hero("Superman"))))
                        .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void whenUpdatingHero_thenReturns200() throws Exception {
        given(service.getHeroById(1L))
                .willReturn(new Hero("Superman"));
        MockHttpServletResponse response =
                mockMvc.perform(
                                put("/heroes/1")
                                        .accept(MediaType.APPLICATION_JSON)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(new Hero("Superman"))))
                        .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}
