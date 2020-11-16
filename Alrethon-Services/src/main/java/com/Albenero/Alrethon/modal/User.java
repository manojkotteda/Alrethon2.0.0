package com.Albenero.Alrethon.modal;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "_User")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String user_UUID;
    private String user_name;
    private String user_email;
    private String user_image_url;

}
