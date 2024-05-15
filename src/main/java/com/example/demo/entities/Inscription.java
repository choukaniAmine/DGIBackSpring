package com.example.demo.entities;



import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.dtos.UserDto;
import com.example.demo.enuum.Identifiant;
import com.example.demo.enuum.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity

@Table(name= "\"inscription\"")

public class Inscription implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "yourGenerator5Name")
    @SequenceGenerator(name = "yourGenerator5Name", sequenceName = "inscription_seq", allocationSize = 1)
    private Long idInscription;

    private String email;
public String valeurIdentifiant;
public Identifiant typeIdentifiant;
    private String password;
    private String name;
    private boolean enabled;
    private String verificationCode;
    private boolean NonLocked;
    private String Poste;
    private java.util.Date dateInscri;

    private UserRole userRole;
    @OneToOne
    @JoinColumn(name = "contribuable_id", unique = true, nullable = false)
 private  Contribuable contribuable;

   
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Contribuable getContribuable() {
		return contribuable;
	}
	public void setContribuable(Contribuable contribuable) {
		this.contribuable = contribuable;
	}
	
	public String getValeurIdentifiant() {
		return valeurIdentifiant;
	}
	public void setValeurIdentifiant(String valeurIdentifiant) {
		this.valeurIdentifiant = valeurIdentifiant;
	}
	public Identifiant getTypeIdentifiant() {
		return typeIdentifiant;
	}
	public void setTypeIdentifiant(Identifiant typeIdentifiant) {
		this.typeIdentifiant = typeIdentifiant;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getIdInscription() {
		return idInscription;
	}
	public void setIdInscription(Long idInscription) {
		this.idInscription = idInscription;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	public boolean isNonLocked() {
		return NonLocked;
	}
	public void setNonLocked(boolean nonLocked) {
		NonLocked = nonLocked;
	}
	public String getPoste() {
		return Poste;
	}
	public void setPoste(String poste) {
		Poste = poste;
	}
	public java.util.Date getDateInscri() {
		return dateInscri;
	}
	public void setDateInscri(java.util.Date dateInscri) {
		this.dateInscri = dateInscri;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
     public String getPassword() {
            return password;
        }
     @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {

            return List.of(new SimpleGrantedAuthority(userRole.name()));
        }


    @Override
    public String getUsername() {

        return email;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return NonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return enabled;
    }
    

public UserDto getUserDto() {
	UserDto user=new UserDto();
	user.setContribuable(contribuable);
	user.setDate(dateInscri);
	user.setEmail(email);
	user.setEnabled(enabled);
	user.setId(idInscription);
	user.setNonLocked(NonLocked);
	user.setPassword(password);
	user.setPoste(Poste);
    user.setTypeIdentifiant(typeIdentifiant);
    user.setUserRole(userRole);
    user.setValeurIdentifiant(valeurIdentifiant);
    return user;
}

}
