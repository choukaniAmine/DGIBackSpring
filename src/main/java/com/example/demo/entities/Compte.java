package com.example.demo.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.dtos.CompteDtos;
import com.example.demo.dtos.UserDto;
import com.example.demo.enuum.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity

@Table(name= "\"compte\"")
public class Compte implements UserDetails {



	    /**
	     
	*/
	  private static final long serialVersionUID = 1L;
	  @Id@GeneratedValue(strategy = GenerationType.AUTO, generator = "yourGenerator6Name")
	  @SequenceGenerator(name = "yourGenerator6Name", sequenceName = "Compte_seq", allocationSize = 1)
	  private Long idCompte;

	    private String email;
	    private String password;
	    private UserRole userRole;
	    private int failedAttempt=0;
	    // Other fields

	    @OneToOne(optional = true)
	    @JoinColumn(name = "inscription_id", referencedColumnName = "idInscription")
	    private Inscription inscription;

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return List.of(new SimpleGrantedAuthority(userRole.name()));
	    }

	    public int getFailedAttempt() {
			return failedAttempt;
		}

		public void setFailedAttempt(int failedAttempt) {
			this.failedAttempt = failedAttempt;
		}

		@Override
	    public String getUsername() {
	        // TODO Auto-generated method stub
	        return email;
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        // TODO Auto-generated method stub
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        // TODO Auto-generated method stub
	        if(userRole==UserRole.Admin || userRole==UserRole.Responsable){
	            return true;
	        }else return inscription.isNonLocked();
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        // TODO Auto-generated method stub
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        // TODO Auto-generated method stub
	        if(userRole==UserRole.Admin || userRole==UserRole.Responsable){
	            return true;
	        }else return inscription.isEnabled();
	    }

		public Long getIdCompte() {
			return idCompte;
		}

		public void setIdCompte(Long idCompte) {
			this.idCompte = idCompte;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public UserRole getUserRole() {
			return userRole;
		}

		public void setUserRole(UserRole userRole) {
			this.userRole = userRole;
		}

		public Inscription getInscription() {
			return inscription;
		}

		public void setInscription(Inscription inscription) {
			this.inscription = inscription;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
public CompteDtos getcompteDtos()
{
	CompteDtos compte = new CompteDtos();
    compte.setIdCompte(idCompte);
    compte.setEmail(email);

    if (inscription != null) {
        UserDto userDto = new UserDto();
        userDto.setId(inscription.getUserDto().getId());
        userDto.setEmail(inscription.getUserDto().getEmail());
        userDto.setUserRole(inscription.getUserDto().getUserRole());
        userDto.setRandomCode(inscription.getUserDto().getRandomCode());
        userDto.setEnabled(inscription.getUserDto().isEnabled());
        userDto.setNonLocked(inscription.getUserDto().isNonLocked());
        userDto.setDate(inscription.getUserDto().getDate());
        userDto.setContribuable(inscription.getUserDto().getContribuable());
        userDto.setNom(inscription.getUserDto().getNom());
       userDto.setPrenom(inscription.getUserDto().getPrenom());
        userDto.setTypeIdentifiant(inscription.getUserDto().getTypeIdentifiant());
        userDto.setValeurIdentifiant(inscription.getUserDto().getValeurIdentifiant());
        userDto.setPoste(inscription.getUserDto().getPoste());
        userDto.setPassword(inscription.getPassword());
        compte.setInscription(userDto);
    } else {
        // Handle the case where inscription is null, maybe set default values or do something else
        // For now, let's just set the inscription to null in the CompteDto
        compte.setInscription(null);
    }

    compte.setPassword(password);
    compte.setUserRole(userRole);

    return compte;
}
}
	  

