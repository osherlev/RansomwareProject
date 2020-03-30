package Agent.Payment;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Bitcoin {

	private String adress;

	public Bitcoin(String adress) {
		this.adress = adress;
	}
}