package it.epicode.be.energy.security.util;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import it.epicode.be.energy.security.model.Role;
import it.epicode.be.energy.security.model.Roles;
import it.epicode.be.energy.security.model.User;
import it.epicode.be.energy.security.repository.RoleRepository;
import it.epicode.be.energy.security.repository.UserRepository;

@Component
public class AddUserSpringRunner implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		if (userRepository.findAll().isEmpty()) {
			Role roleA = new Role();
			roleA.setRoleName(Roles.ROLE_ADMIN);
			roleRepository.save(roleA);

			Role roleU = new Role();
			roleU.setRoleName(Roles.ROLE_USER);
			roleRepository.save(roleU);

			User userA = new User();
			userA.setUserName("admin");
			userA.setPassword(bCrypt.encode("admin"));
			userA.setEmail("admin@domain.com");
			userA.setActive(true);
			Set<Role> rolesA = new HashSet<>();
			rolesA.add(roleA);
			rolesA.add(roleU);
			userA.setRoles(rolesA);
			userRepository.save(userA);

			User userU = new User();
			userU.setUserName("user");
			userU.setPassword(bCrypt.encode("user"));
			userU.setEmail("user@domain.com");
			userU.setActive(true);
			Set<Role> rolesU = new HashSet<>();
			rolesU.add(roleU);
			userU.setRoles(rolesU);
			userRepository.save(userU);

		}
	}

}
