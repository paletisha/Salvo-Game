package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository repository, GameRepository gameRep, GamePlayerRepository gamePlayerRep, ShipRepository shipRepository, SalvoRepository salvoRepository, ScoreRepository scoreRepository) {
		return (args) -> {
			// save a couple of customers
			Player p1 = new Player("j.bauer@gmail.com", "Jack","24");
			repository.save(p1);

			Player p2 = new Player("c.obrian@gmail.com", "Chloe","42");
			repository.save(p2);

			Player p3 = new Player("k.bauer@gmail.com", "Kim","kb");
			repository.save(p3);

			Player p4 = new Player("t.almeida@gmail.com", "Tony","mole");
			repository.save(p4);

			Player p5 = new Player("m.dessler@gmail.com", "Michelle", "lol");
			repository.save(p5);

			Game g1 = new Game();
			gameRep.save(g1);

			Game g2 = new Game();
			gameRep.save(g2);

			Game g3 = new Game();
			gameRep.save(g3);

			Game g4 = new Game();
			gameRep.save(g4);

			Game g5 = new Game();
			gameRep.save(g5);

			Game g6 = new Game();
			gameRep.save(g6);

			Game g7 = new Game();
			gameRep.save(g7);

			Game g8 = new Game();
			gameRep.save(g8);


			GamePlayer gp1 = new GamePlayer(p1,g1);
			GamePlayer gp2 = new GamePlayer(p2,g1);
			GamePlayer gp3 = new GamePlayer(p1,g2);
			GamePlayer gp4 = new GamePlayer(p2,g2);
			GamePlayer gp5 = new GamePlayer(p2,g3);
			GamePlayer gp6 = new GamePlayer(p4,g3);
			GamePlayer gp7 = new GamePlayer(p2,g4);
			GamePlayer gp8 = new GamePlayer(p1,g4);
			GamePlayer gp9 = new GamePlayer(p3,g5);
			GamePlayer gp10 = new GamePlayer(p1,g5);
			GamePlayer gp11 = new GamePlayer(p4,g6);
			GamePlayer gp12 = new GamePlayer(p4,g8);
			GamePlayer gp13 = new GamePlayer(p3,g8);
			GamePlayer gp14 = new GamePlayer(p1,g7);





//Game 1

			List<String> destroyer1 = new ArrayList<>();

			List <String> patrolBoat1 = new ArrayList<>();
			List <String> submarine1 = new ArrayList<>();

			destroyer1.add("H2");
			destroyer1.add("H3");
			destroyer1.add("H4");
			patrolBoat1.add("B4");
			patrolBoat1.add("B5");
			submarine1.add("E1");
			submarine1.add("F1");
			submarine1.add("G1");




			List<String> destroyer2 = new ArrayList<>();

			List <String> patrolBoat2 = new ArrayList<>();

			destroyer2.add("B5");
			destroyer2.add("C5");
			destroyer2.add("D5");
			patrolBoat2.add("F1");
			patrolBoat2.add("F2");

//Game 2

			List <String> destroyer3 = new ArrayList<>();
			List <String> patrolBoat3 = new ArrayList<>();


			destroyer3.add("B5");
			destroyer3.add("C5");
			destroyer3.add("D5");
			patrolBoat3.add("C6");
			patrolBoat3.add("C7");

			List <String> patrolBoat4 = new ArrayList<>();
			List <String> submarine4 = new ArrayList<>();

			submarine4.add("A2");
			submarine4.add("A3");
			submarine4.add("A4");
			patrolBoat4.add("G6");
			patrolBoat4.add("H6");


//Game3
			List <String> destroyer5 = new ArrayList<>();
			List <String> patrolBoat5 = new ArrayList<>();

			destroyer5.add("B5");
			destroyer5.add("C5");
			destroyer5.add("D5");
			patrolBoat5.add("C6");
			patrolBoat5.add("C7");

			List <String> patrolBoat6 = new ArrayList<>();
			List <String> submarine6 = new ArrayList<>();

			submarine6.add("A2");
			submarine6.add("A3");
			submarine6.add("A4");
			patrolBoat6.add("G6");
			patrolBoat6.add("H6");


			//Game4

			List <String> destroyer7 = new ArrayList<>();
			List <String> patrolBoat7 = new ArrayList<>();

			destroyer7.add("B5");
			destroyer7.add("C5");
			destroyer7.add("D5");
			patrolBoat7.add("C6");
			patrolBoat7.add("C7");

			List <String> patrolBoat8 = new ArrayList<>();
			List <String> submarine8 = new ArrayList<>();

			submarine8.add("A2");
			submarine8.add("A3");
			submarine8.add("A4");
			patrolBoat8.add("G6");
			patrolBoat8.add("H6");

// Game 5
			List <String> destroyer9 = new ArrayList<>();
			List <String> patrolBoat9 = new ArrayList<>();

			destroyer9.add("B5");
			destroyer9.add("C5");
			destroyer9.add("D5");
			patrolBoat9.add("C6");
			patrolBoat9.add("C7");

			List <String> patrolBoat10 = new ArrayList<>();
			List <String> submarine10 = new ArrayList<>();

			submarine10.add("A2");
			submarine10.add("A3");
			submarine10.add("A4");
			patrolBoat10.add("G6");
			patrolBoat10.add("H6");

		//Game 6

			List <String> destroyer11 = new ArrayList<>();
			List <String> patrolBoat11 = new ArrayList<>();

			destroyer11.add("B5");
			destroyer11.add("C5");
			destroyer11.add("D5");
			patrolBoat11.add("C6");
			patrolBoat11.add("C7");
		//Game 8

			List <String> destroyer12 = new ArrayList<>();
			List <String> patrolBoat12 = new ArrayList<>();

			destroyer12.add("B5");
			destroyer12.add("C5");
			destroyer12.add("D5");
			patrolBoat12.add("C6");
			patrolBoat12.add("C7");

			List <String> patrolBoat13 = new ArrayList<>();
			List <String> submarine13 = new ArrayList<>();

			submarine13.add("A2");
			submarine13.add("A3");
			submarine13.add("A4");
			patrolBoat13.add("G6");
			patrolBoat13.add("H6");







			Ship s1 = new Ship("destroyer", destroyer1);
			Ship s2 = new Ship("patrolBoat",patrolBoat1);
			Ship s3 = new Ship("submarine",submarine1);
			Ship s4 = new Ship("destroyer", destroyer2);
			Ship s5 = new Ship("patrolBoat",patrolBoat2);
			Ship s6 = new Ship("destroyer",destroyer3);
			Ship s7 = new Ship("patrolBoat",patrolBoat3);
			Ship s8 = new Ship("submarine",submarine4);
			Ship s9 = new Ship("patrolBoat",patrolBoat4);
			Ship s10 = new Ship("destroyer",destroyer5);
			Ship s11 = new Ship("patrolBoat",patrolBoat5);
			Ship s12 = new Ship("patrolBoat",patrolBoat6);
			Ship s13 = new Ship("submarine",submarine6);
			Ship s14 = new Ship("destroyer",destroyer7);
			Ship s15 = new Ship("patrolBoat",patrolBoat7);
			Ship s16 = new Ship("patrolBoat",patrolBoat8);
			Ship s17 = new Ship("submarine",submarine8);
			Ship s18 = new Ship("destroyer",destroyer9);
			Ship s19 = new Ship("patrolBoat",patrolBoat9);
			Ship s20 = new Ship("patrolBoat",patrolBoat10);
			Ship s21 = new Ship("submarine",submarine10);
			Ship s22 = new Ship("destroyer",destroyer11);
			Ship s23 = new Ship("patrolBoat",patrolBoat11);
			Ship s24 = new Ship("destroyer",destroyer12);
			Ship s25 = new Ship("patrolBoat",patrolBoat12);
			Ship s26 = new Ship("patrolBoat",patrolBoat13);
			Ship s27 = new Ship("submarine",submarine13);

			List <String> salvo1 = new ArrayList<>();

			salvo1.add("B3");

			Salvo sa1 = new Salvo(1, gp1, salvo1);

			List <String> salvo2 = new ArrayList<>();
			salvo2.add("D7");
			Salvo sa2 = new Salvo(1, gp2, salvo2);

			List <String> salvo3 = new ArrayList<>();
			salvo3.add("F1");
			Salvo sa3 = new Salvo(2, gp1, salvo3);

			List <String> salvo4 = new ArrayList<>();
			salvo4.add("C3");
			Salvo sa4 = new Salvo(2, gp2, salvo4);

//add ships to gameplayers

			gp1.addShip(s1);
			gp1.addShip(s2);
			gp1.addShip(s3);
			gp2.addShip(s4);
			gp2.addShip(s5);
			gp3.addShip(s6);
			gp3.addShip(s7);
			gp4.addShip(s8);
			gp4.addShip(s9);
			gp5.addShip(s10);
			gp5.addShip(s11);
			gp6.addShip(s12);
			gp6.addShip(s13);
			gp7.addShip(s14);
			gp7.addShip(s15);
			gp8.addShip(s16);
			gp8.addShip(s17);
			gp9.addShip(s18);
			gp9.addShip(s19);
			gp10.addShip(s20);
			gp10.addShip(s21);
			gp11.addShip(s22);
			gp11.addShip(s23);
			gp12.addShip(s24);
			gp12.addShip(s25);
			gp13.addShip(s26);
			gp13.addShip(s27);

		//add salvos to gameplayers

		gp1.addSalvo(sa1);
		gp1.addSalvo(sa3);
		gp2.addSalvo(sa2);
		gp2.addSalvo(sa4);



			gamePlayerRep.save(gp1);
			gamePlayerRep.save(gp2);
			gamePlayerRep.save(gp3);
			gamePlayerRep.save(gp4);
			gamePlayerRep.save(gp5);
			gamePlayerRep.save(gp6);
			gamePlayerRep.save(gp7);
			gamePlayerRep.save(gp8);
			gamePlayerRep.save(gp9);
			gamePlayerRep.save(gp10);
			gamePlayerRep.save(gp11);
			gamePlayerRep.save(gp12);
			gamePlayerRep.save(gp13);
			gamePlayerRep.save(gp14);





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
			shipRepository.save(s15);
			shipRepository.save(s16);
			shipRepository.save(s18);
			shipRepository.save(s19);
			shipRepository.save(s20);
			shipRepository.save(s21);
			shipRepository.save(s22);
			shipRepository.save(s23);
			shipRepository.save(s24);
			shipRepository.save(s25);
			shipRepository.save(s26);
			shipRepository.save(s27);

			salvoRepository.save(sa1);
			salvoRepository.save(sa2);
			salvoRepository.save(sa3);
			salvoRepository.save(sa4);


			scoreRepository.save(new Score(1.0, new Date(), g1, p1));
			scoreRepository.save(new Score(0.0, new Date(), g1, p2));


		};
	}








}

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	PlayerRepository playerRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(inputName-> {
			Player player = playerRepository.findByEmail(inputName);
			if (player != null) {
				return new User(player.getEmail(), passwordEncoder.encode(player.getPassword()),
						AuthorityUtils.createAuthorityList("USER"));
			} else {
				throw new UsernameNotFoundException("Unknown user: " + inputName);
			}
		});
	}


}

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/web/games.html").hasAuthority("USER")
				.antMatchers("/web/games.css").hasAuthority("USER")
				.antMatchers("/web/games.js").hasAuthority("USER")
				.antMatchers("/web/game.html").hasAuthority("USER")
				.antMatchers("/web/game.css").hasAuthority("USER")
				.antMatchers("/web/game.js").hasAuthority("USER")
				.antMatchers("/web/index.html").permitAll()
				.antMatchers("/web/api/players").permitAll();


				http.formLogin()
				.usernameParameter("email")
				.passwordParameter("pwd")
				.loginPage("/api/login");

		http.logout().logoutUrl("/api/logout");
		// turn off checking for CSRF tokens
		http.csrf().disable();

		// if user is not authenticated, just send an authentication failure response
		http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

		// if login is successful, just clear the flags asking for authentication
		http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

		// if login fails, just send an authentication failure response
		http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

		// if logout is successful, just send a success response
		http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
	}

	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
	}
	}

