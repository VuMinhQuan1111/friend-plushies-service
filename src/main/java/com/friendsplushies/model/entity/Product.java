package com.friendsplushies.model.entity;


import com.friendsplushies.model.entity.listener.FEntity;
import com.friendsplushies.model.entity.listener.FEntityListener;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Getter
@Setter
@Table(name = "product")
@EntityListeners(FEntityListener.class)
public class Product implements FEntity, Serializable {
  @Id
  @SequenceGenerator(name = "productGenerator", sequenceName = "product_product_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "productGenerator")
  @Column(name = "product_id")
  private Long productId;

  @Column(name = "product_name")
  private String name;

  @Column(name = "category_id")
  private Long categoryId;

  @Column(name = "product_size")
  private Double size;

  @Column(name = "product_image")
  private String imageUrl;

  @Column(name = "product_description")
  private String description;

  @Column(name = "product_price")
  private BigDecimal price;

  @Column(name = "created_date")
  private Timestamp createdDate;

  @Column(name = "created_by")
  private String createdBy;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", referencedColumnName = "category_id", updatable = false, insertable = false)
  @JsonBackReference
  private Category category;

}
