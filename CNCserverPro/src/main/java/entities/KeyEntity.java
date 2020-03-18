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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KeyEntity<T> {

	@Id
	String ip;
	@Column(nullable = false, unique = true)
	T key;
	String algorithem;
	
	public KeyEntity(String ip, T key, String encAl) {
		// TODO Auto-generated constructor stub
		this.algorithem=encAl;
		this.ip=ip;
		this.key=key;
	}

	
}
