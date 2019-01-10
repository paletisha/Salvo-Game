package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository repository, GameRepository gameRep, GamePlayerRepository gamePlayerRep, ShipRepository shipRepository) {
		return (args) -> {
			// save a couple of customers
			Player p1 = new Player("j.bauer@gmail.com", "Jack");
			repository.save(p1);

			Player p2 = new Player("c.obrian@gmail.com", "Chloe");
			repository.save(p2);

			Player p3 = new Player("k.bauer@gmail.com", "Kim");
			repository.save(p3);

			Player p4 = new Player("d.palmer@gmail.com", "David");
			repository.save(p4);

			Player p5 = new Player("m.dessler@gmail.com", "Michelle");
			repository.save(p5);

			Game g1 = new Game();
			gameRep.save(g1);

			Game g2 = new Game();
			gameRep.save(g2);

			Game g3 = new Game();
			gameRep.save(g3);

			GamePlayer gp1 = new GamePlayer(p1,g1);
			GamePlayer gp2 = new GamePlayer(p2,g1);
			GamePlayer gp3 = new GamePlayer(p3,g2);
			GamePlayer gp4 = new GamePlayer(p4,g2);
			GamePlayer gp5 = new GamePlayer(p5,g3);





			List<String> destroyer = new ArrayList<>();
			List <String> cruiser = new ArrayList<>();
			List <String> battleship = new ArrayList<>();
			List <String> boat = new ArrayList<>();

			destroyer.add("A2");
			destroyer.add("A3");
			destroyer.add("A4");
			cruiser.add("C7");
			cruiser.add("C6");
			battleship.add("J5");
			battleship.add("J6");
			battleship.add("J7");
			battleship.add("J8");
			boat.add("B2");
			boat.add("B3");
			boat.add("B4");
			boat.add("B5");


			Ship s1 = new Ship("destroyer", destroyer);
			Ship s2 = new Ship("cruiser",cruiser);
			Ship s3 = new Ship("battleship", battleship);
			Ship s4 = new Ship("boat",boat);
			Ship s5 = new Ship("boat",boat);
			Ship s6 = new Ship("boat",boat);
			Ship s7 = new Ship("boat",boat);
			Ship s8 = new Ship("boat",boat);
			Ship s9 = new Ship("boat",boat);
			Ship s10 = new Ship("boat",boat);
			Ship s11= new Ship("boat",boat);
			Ship s12 = new Ship("boat",boat);
			Ship s13 = new Ship("boat",boat);
			Ship s14= new Ship("boat",boat);



			gp1.addShip(s1);
			gp1.addShip(s2);
			gp1.addShip(s3);
			gp1.addShip(s4);

			gp2.addShip(s3);
			gp2.addShip(s4);
			gp2.addShip(s5);
			gp2.addShip(s6);
			gp3.addShip(s7);
			gp3.addShip(s8);
			gp3.addShip(s9);
			gp3.addShip(s10);


			gp4.addShip(s11);
			gp4.addShip(s12);
			gp4.addShip(s13);
			gp4.addShip(s14);

			gamePlayerRep.save(gp1);
			gamePlayerRep.save(gp2);
			gamePlayerRep.save(gp3);
			gamePlayerRep.save(gp4);
			gamePlayerRep.save(gp5);

			shipRepository.save(s1);
			shipRepository.save(s2);
			shipRepository.save(s3);
			shipRepository.save(s4);
			shipRepository.save(s5);
			shipRepository.save(s6);
			shipRepository.save(s7);
			shipRepository.save(s8);
			shipRepository.save(s9);
			shipRepository.save(s10);
			shipRepository.save(s11);
			shipRepository.save(s12);
			shipRepository.save(s13);
			shipRepository.save(s14);

		};
	}








}


