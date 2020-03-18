package controllers;

import java.util.Optional;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import entities.KeyEntity;
import repositories.KeyRepository;

@RestController
public class KeyController {
	@Inject
	private KeyRepository repository;

	// should be POST
	@GetMapping("/Keys")
	public @ResponseBody <T> KeyEntity<?> createKeyData(@RequestParam String ip, @RequestParam T key,
			@RequestParam String encAl) {
		return (KeyEntity<?>) repository.save(new KeyEntity<T>(ip, key, encAl));

	}

	// GET
	@GetMapping("/findKey")
	public KeyEntity<?> findKey(@RequestParam String ip) {
		@SuppressWarnings("rawtypes")
		Optional<KeyEntity> key = repository.findById(ip);
		return key.get();

	}

}
