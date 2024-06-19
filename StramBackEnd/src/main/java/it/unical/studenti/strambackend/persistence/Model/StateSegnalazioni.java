package it.unical.studenti.strambackend.persistence.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type"
)
@JsonSubTypes({
		@JsonSubTypes.Type(value = NuovaSegnalazione.class, name = "NuovaSegnalazione"),
		@JsonSubTypes.Type(value = InLavorazioneSegnalazione.class, name = "InLavorazioneSegnalazione"),
		@JsonSubTypes.Type(value = CompletataSegnalazione.class, name = "CompletataSegnalazione")
})

public abstract class StateSegnalazioni {
	@JsonBackReference
	Segnalazioni segnalazione;
	
	StateSegnalazioni(Segnalazioni segnalazione) {
		this.segnalazione=segnalazione;
	}
	public abstract String  statoSegnalazione();

	public Segnalazioni getSegnalazione() {
		return segnalazione;
	}
}
