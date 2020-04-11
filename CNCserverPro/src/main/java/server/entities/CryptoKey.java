package server.entities;

import java.io.Serializable;

import javax.crypto.SecretKey;
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
@Entity
@Getter
@Setter
@AllArgsConstructor

public class CryptoKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	String ip;
	@Column(nullable = false)
	SecretKey key;
	@Column(nullable = false)
	String algorithm;

}