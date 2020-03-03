package springboot.thymeleaf.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;
    
    @NotBlank(message = "username is mandatory")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "password is mandatory")
    @Column(name = "password")
    private String password;
    
    @Column(name = "roles")
    private String roles; 
   
    
    public Student() {}

    public Student(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }    
  
    public String getName() {
        return name;
    }   

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", roles="
				+ roles + "]";
	}

	
}