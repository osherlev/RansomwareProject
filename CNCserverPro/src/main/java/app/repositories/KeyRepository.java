package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.CryptoKey;



@Repository
public interface KeyRepository extends JpaRepository<CryptoKey, String> {

	
}
