package server.entities;

import java.security.Key;

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
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Table(name = "Keys")
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class CryptoKey {


	
	@Id
	String ip;
	@Column(nullable = false)
	Key key;
	@Column(nullable = false)
	String algorithm;

}