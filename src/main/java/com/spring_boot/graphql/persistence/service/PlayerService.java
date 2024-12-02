package com.spring_boot.graphql.persistence.service;

import com.spring_boot.graphql.persistence.model.Player;
import com.spring_boot.graphql.persistence.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    AtomicInteger id = new AtomicInteger(0);

    private List<Player> playerList = new ArrayList<>();

    public List<Player> findAll() {
        return playerList;
    }

    public Optional<Player> findOne(Integer id) {
        return playerList.stream().filter(player -> Objects.equals(player.id(), id)).findFirst();
    }

    public Player create(String name, Team team) {
        Player player = new Player(id.incrementAndGet(), name, team);
        playerList.add(player);
        return player;
    }

    public Player delete(Integer id) {
        Player player = playerList.stream().filter(p -> Objects.equals(p.id(), id))
                .findFirst().orElseThrow(IllegalArgumentException::new);
        playerList.remove(player);
        return player;
    }

    public Player update(Integer id, String name, Team team) {
        Player updatePlayer = new Player(id, name, team);
        Optional<Player> optional = playerList.stream().filter(p -> Objects.equals(p.id(), id)).findFirst();

        if (optional.isPresent()) {
            Player player = optional.get();
            int index = playerList.indexOf(player);
            playerList.set(index, updatePlayer);
        } else {
            throw new IllegalArgumentException("Invalid Player");
        }
        return updatePlayer;
    }

    @PostConstruct
    private void init() {
        playerList.add(new Player(id.incrementAndGet(), "MS Dhoni", Team.CSK));
        playerList.add(new Player(id.incrementAndGet(), "Rohit Sharma", Team.MI));
        playerList.add(new Player(id.incrementAndGet(), "Jasprit Bumrah", Team.MI));
        playerList.add(new Player(id.incrementAndGet(), "Rishabh Pant", Team.DC));
        playerList.add(new Player(id.incrementAndGet(), "Hardik Pandya", Team.GT));
    }


}
