package com.pruebatecnica.w2m;

import com.pruebatecnica.w2m.hero.Hero;
import com.pruebatecnica.w2m.hero.HeroException;
import com.pruebatecnica.w2m.hero.HeroService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@ActiveProfiles("test")
class W2mApplicationTests {

    @Autowired
    HeroService service;

    @Test
    void whenLoadAll_thenAllHeroesWillBeFound() {
        System.out.println(service.findAll());
    }

    @Test
    void givenAText_thenAllHeroesWillBeFound() {
        final String text = "man";
        System.out.println(service.getHeroesByName(text));
    }

    @Test
    void whenValidId_thenHeroWillBeFound() {
        final Hero supermanHero = new Hero("Superman");

        try {
            final Hero hero = service.getHeroById(1L);
            assertThat(supermanHero.getName()).isEqualTo(hero.getName());
        } catch (HeroException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void whenValidHero_thenHeroWillBeAdded() {
        final Hero hero = new Hero("Daredevil");
        service.saveHero(hero);
        System.out.println(service.findAll());
    }

    @Test
    void whenExistHero_thenHeroWillBeUpdated() throws HeroException {
        Hero hero = service.getHeroById(3L);
        if(hero == null){
           fail("Hero does not exist");
        }
        hero.setName("Heroe Modificado");
        service.saveHero(hero);
        System.out.println(hero);
    }

}
