package com.server.app.entities;

import javax.crypto.SecretKey;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "CryptoKeys")
@NoArgsConstructor
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