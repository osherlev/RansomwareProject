package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Keys")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CryptoKey<T> {
	@Id
	String ip;
	@Column(nullable = false, unique = true)
	T key;
	@Column(nullable = false)
	String algorithem;

	public CryptoKey(String ip2, T key2, String encalgo) {
		this.ip=ip2;
		this.key=key2;
		this.algorithem=encalgo;
		
	}
}
