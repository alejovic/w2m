package com.pruebatecnica.w2m.hero;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class HeroService {

    private static final Logger logger = LoggerFactory.getLogger(HeroService.class);

    @Autowired
    HeroRepository repository;

    @Cacheable("heroes")
    public List<Hero> findAll() {
        try {
            logger.warn("Simulando delay...");
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        if (hero.isPresent()) {
            return hero.get();
        }
        throw new HeroException(HeroException.ERROR_HERO_NOT_FOUND, "The user does not exist in the repository.");
    }

    public Hero saveHero(Hero hero) {
        return repository.save(hero);
    }

}
