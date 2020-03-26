package server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Keys")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class CryptoKey<T> {
	@Id
	String ip;
	@Column(nullable = false)
	T key;
	@Column(nullable = false)
	String algorithm;

	public CryptoKey(String ip, T key, String encalgo) {
		this.ip = ip;
		this.key = key;
		this.algorithm = encalgo;

	}

	

}