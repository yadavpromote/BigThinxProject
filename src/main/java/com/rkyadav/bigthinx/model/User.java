package com.rkyadav.bigthinx.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rkyadav
 */
@Document(collection = "user")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class User {

    //Generate id from db
    @Id 
    private String id;

    //user name should not be null
    @NotNull
    @Size(min = 1)
    private String name;
    
    //compatible data of birth format
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    
    private String address;
    private String description;
    
    //create date 
    private Date createdAt = new Date();

    public User() {
        super();
    }
    
    public User(String name, Date dateOfBirth, String address, String description) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", address=" + address + ", description=" + description + ", createdAt=" + createdAt + '}';
    }
    
}
