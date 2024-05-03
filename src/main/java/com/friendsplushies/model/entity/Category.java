package com.friendsplushies.model.entity;


import com.friendsplushies.model.entity.listener.FEntity;
import com.friendsplushies.model.entity.listener.FEntityListener;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@Getter
@Setter
@Table(name = "category")
@EntityListeners(FEntityListener.class)
public class Category implements FEntity, Serializable {
  @Id
  @SequenceGenerator(name = "categoryGenerator", sequenceName = "category_category_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "categoryGenerator")
  @Column(name = "category_id")
  private Long categoryId;

  @Column(name = "category_name")
  private String name;

  @Column(name = "category_description")
  private String description;

  @Column(name = "created_date")
  private Timestamp createdDate;

  @Column(name = "created_by")
  private String createdBy;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
  @JsonManagedReference
  private List<Product> products;
}
