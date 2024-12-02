package com.spring_boot.graphql.persistence.controller;

import com.spring_boot.graphql.persistence.model.Player;
import com.spring_boot.graphql.persistence.model.Team;
import com.spring_boot.graphql.persistence.service.PlayerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

//@QueryMapping -> handle query operations.
//A query is typically used to fetch data.

    // Query schema name should be same a controller method name (findAll)
    @QueryMapping
    public List<Player> findAll() {
        return playerService.findAll();
    }

    @QueryMapping
    public Optional<Player> findOne(@Argument Integer id) {
        return playerService.findOne(id);
    }

//@MutationMapping -> Mutation operations.
//A mutation is typically used to modify data (e.g., create, update, delete).

    @MutationMapping
    public Player create(@Argument String name, @Argument Team team) {
        return playerService.create(name, team);
    }

    @MutationMapping
    public Player update(@Argument Integer id, @Argument String name, @Argument Team team) {
        return playerService.update(id, name, team);
    }

    @MutationMapping
    public Player delete(@Argument Integer id) {
        return playerService.delete(id);
    }
}
