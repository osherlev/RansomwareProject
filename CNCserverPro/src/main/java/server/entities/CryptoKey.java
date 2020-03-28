package server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Keys")
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AllArgsConstructor
public class CryptoKey<T> {

	@Id
	String ip;
	@Column(nullable = false)
	T key;
	@Column(nullable = false)
	String algorithm;



}