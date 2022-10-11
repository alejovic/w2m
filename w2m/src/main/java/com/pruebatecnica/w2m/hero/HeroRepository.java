package com.pruebatecnica.w2m.hero;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HeroRepository extends CrudRepository<Hero, Long> {

    List<Hero> findHeroByNameContaining(String name);

}
