package ch.zli.m223.model;
import jakarta.persistence.*;


  @Entity 
  public class Category{
    @Id
    @GeneratedValue 
    private Long id;

    @Column(nullable = false)
    private String title;

    public long getId(){
      return id;
    }

  public String getTitle(){
    return title;
  }

  public void setTitle(String title){
    this.title = title;
  }
}