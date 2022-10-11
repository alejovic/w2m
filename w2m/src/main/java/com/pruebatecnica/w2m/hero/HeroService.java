package com.pruebatecnica.w2m.hero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class HeroService {

    @Autowired
    HeroRepository repository;

    public List<Hero> findAll(){
        final List<Hero> heroes = new LinkedList<>();
        Iterable<Hero> iterator = repository.findAll();
        iterator.forEach(heroes::add);
        return heroes;
    }

    public List<Hero> getHeroesByName(String name) {
        return repository.findHeroByNameContaining(name);
    }

    public Hero getHeroById(Long id) throws HeroException {
        Optional<Hero> hero = repository.findById(id);
        if(hero.isPresent()){
            return hero.get();
        }
        throw new HeroException(HeroException.ERROR_HERO_NOT_FOUND, "The user does not exist in the repository.");
    }

    public void saveHero(Hero hero){
        repository.save(hero);
    }

}
