package it.opensource.ecompany.domain;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "images_product")
@Entity
public class ImageProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image_byte")
    private byte[] imageByte;

    public ImageProduct(byte[] imageByte) {

        this.imageByte = imageByte;
    }

    public ImageProduct() {

    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public byte[] getImageByte() {

        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {

        this.imageByte = imageByte;
    }
}
