package server.entities;

import javax.crypto.SecretKey;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Table(name = "Keys")
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter
@AllArgsConstructor


public class CryptoKey {
	
	@Id
	String ip;
	@Column(nullable = false)
	SecretKey key;
	@Column(nullable = false)
	String algorithm;

}