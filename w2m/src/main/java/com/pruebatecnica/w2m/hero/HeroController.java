package com.pruebatecnica.w2m.hero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/heroes")
public class HeroController {

    @Autowired
    HeroService service;

    @GetMapping
    public ResponseEntity<List<Hero>> getAllHeroes() {
        try {
            final List<Hero> heroes = service.findAll();
            if (heroes == null || (heroes != null && heroes.isEmpty())) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(heroes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hero> getHeroById(@PathVariable("id") Long id) {
        try {
            final Hero hero = service.getHeroById(id);
            return new ResponseEntity<>(hero, HttpStatus.OK);
        } catch (HeroException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Hero> createHero(@RequestBody Hero hero) {
        Hero _hero = service.saveHero(new Hero(hero.getName()));
        return new ResponseEntity<>(_hero, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hero> updateHero(@PathVariable("id") Long id, @RequestBody Hero hero) {
        try {
            final Hero _hero = service.getHeroById(id);
            _hero.setName(hero.getName());
            service.saveHero(_hero);
            return new ResponseEntity<>(hero, HttpStatus.OK);
        } catch (HeroException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
